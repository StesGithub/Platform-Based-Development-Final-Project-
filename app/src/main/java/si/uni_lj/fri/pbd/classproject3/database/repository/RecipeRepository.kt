package si.uni_lj.fri.pbd.classproject3.database.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import si.uni_lj.fri.pbd.classproject3.database.RecipeDatabase
import si.uni_lj.fri.pbd.classproject3.database.dao.RecipeDao
import si.uni_lj.fri.pbd.classproject3.database.entity.RecipeDetails
import si.uni_lj.fri.pbd.classproject3.models.Mapper
import si.uni_lj.fri.pbd.classproject3.models.dto.RecipeByIdResponseDTO
import si.uni_lj.fri.pbd.classproject3.models.dto.RecipeByIngredientResponseDTO
import si.uni_lj.fri.pbd.classproject3.models.dto.RecipesByIdDTO
import si.uni_lj.fri.pbd.classproject3.models.dto.RecipesByIngredientDTO
import si.uni_lj.fri.pbd.classproject3.rest.RestAPI

class RecipeRepository(
    context: Context,
    private val apiInstance: RestAPI,
    private val recipeDao: RecipeDao
) {



    var recipes = MutableLiveData<List<RecipesByIngredientDTO>?>(null)
    var recipeDetails = MutableLiveData<RecipeDetails?>()


    fun populateSearchByIngredient(searchItem: String) {
        Log.d("TEST", "Made it to the repository. $searchItem")

        apiInstance.getRecipeByIngredient(searchItem).enqueue(object :
            Callback<RecipeByIngredientResponseDTO> {

            override fun onResponse(
                call: Call<RecipeByIngredientResponseDTO>,
                response: Response<RecipeByIngredientResponseDTO>
            ) {
                if (response.body() != null) {
                    val responseWoohoo = response.body()?.RecipeByIngredientList
                    if (!responseWoohoo.isNullOrEmpty()) {
                        recipes.value = responseWoohoo
                        val mealRepsonseArray = responseWoohoo[0]
                        for (item in responseWoohoo) {
                            Log.d("Test", "Got ${item.strMeal}")

                        }
                    }

                }
            }

            override fun onFailure(p0: Call<RecipeByIngredientResponseDTO>, p1: Throwable) {
                Log.d("TEST", "Failed API call: ${p1.message}")
            }

        })
    }

    suspend fun populateDetailScreenById(clickItem: String) {
        Log.d("TEST", "Made it here (repository) $clickItem")

        val recipeFromDB = compareRecipeIdDb(clickItem)
        if(recipeFromDB ==null){
            apiInstance.getRecipeById(clickItem).enqueue(object : Callback<RecipeByIdResponseDTO> {

                override fun onResponse(
                    call: Call<RecipeByIdResponseDTO>,
                    response: Response<RecipeByIdResponseDTO>
                ) {
                    if (response.body() != null) {
                        val responseWoohoo = response.body()?.meals
                        if (!responseWoohoo.isNullOrEmpty()) {
                            val recipe = responseWoohoo[0]
                            val recipeEntry = Mapper.mapRecipeDetailsDtoToRecipeDetails(isFavorite = false, dto = recipe)
                            recipeDetails.value = recipeEntry
                            Log.d("TEST", "Got ${recipeEntry.strMeal} in the repository")

                        } else {
                            recipeDetails.value = null
                        }

                    }
                }

                override fun onFailure(p0: Call<RecipeByIdResponseDTO>, p1: Throwable) {
                    Log.d("TEST", "Failed API call: ${p1.message}")
                }

            })

        }
        else{
            recipeDetails.postValue(recipeFromDB)
        }


    }

    fun populateFavouriteScreenById(recipe: RecipeDetails) {
        if(recipe.isFavorite != true){
            recipe.isFavorite = true
        }
        RecipeDatabase.databaseWriteExecutor.execute(Runnable {
            recipeDao.insertRecipe(recipe)
        })



    }

    fun deleteFavouriteById(idMeal: String) {

        RecipeDatabase.databaseWriteExecutor.execute(Runnable {
            recipeDao.deleteByID(idMeal)
        })
    }

    fun getAllFavourites(): LiveData<List<RecipeDetails>> {
        return recipeDao.getRecipeFavourites(true)

    }


    suspend fun compareRecipeIdDb(idMeal: String): RecipeDetails? {

            return recipeDao.getRecipeById(idMeal)



    }

}
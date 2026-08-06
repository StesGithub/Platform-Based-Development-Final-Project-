package si.uni_lj.fri.pbd.classproject3.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import si.uni_lj.fri.pbd.classproject3.database.entity.RecipeDetails
import si.uni_lj.fri.pbd.classproject3.database.repository.RecipeRepository


class SearchViewModel(private val repository: RecipeRepository) : ViewModel() {

    var recipes = repository.recipes
    var recipeDetail = repository.recipeDetails
    val favourites: LiveData<List<RecipeDetails>> = repository.getAllFavourites()


    fun searchByIngredient(searchItem: String) {
        repository.populateSearchByIngredient(searchItem)
    }

    suspend fun searchById(mealId: String) {
        repository.populateDetailScreenById(mealId)
    }

    fun addToFavourites(recipeDetails: RecipeDetails) {
        repository.populateFavouriteScreenById(recipeDetails)
    }

    fun removeFromFavourites(idMeal: String) {
        repository.deleteFavouriteById(idMeal)
    }


}
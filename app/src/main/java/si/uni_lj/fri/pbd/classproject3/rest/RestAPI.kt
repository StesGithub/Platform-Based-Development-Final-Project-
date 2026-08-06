package si.uni_lj.fri.pbd.classproject3.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import si.uni_lj.fri.pbd.classproject3.models.dto.*


interface RestAPI {


    @get:GET("list.php?i=list")
    val allIngredients: Call<IngredientsDTO?>?

    @GET("filter.php")
    fun getRecipeByIngredient(@Query("i") ingredient: String): Call<RecipeByIngredientResponseDTO>

    @GET("lookup.php")
    fun getRecipeById(@Query("i") idMeal: String): Call<RecipeByIdResponseDTO>

}


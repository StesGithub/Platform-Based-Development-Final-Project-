package si.uni_lj.fri.pbd.classproject3.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import si.uni_lj.fri.pbd.classproject3.database.entity.RecipeDetails

@Dao
interface RecipeDao {

    @Query("SELECT * FROM RecipeDetails WHERE idMeal = :idMeal")
    suspend fun getRecipeById(idMeal: String?): RecipeDetails?



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeDetails)

    @Delete
    fun deleteRecipe(recipe: RecipeDetails)


    @Query("DELETE FROM RecipeDetails WHERE idMeal = :idMeal")
    fun deleteByID(idMeal: String?)


    @Query("SELECT * FROM RecipeDetails WHERE isFavorite = :isFavourite")
    fun getRecipeFavourites(isFavourite: Boolean): LiveData<List<RecipeDetails>>

}
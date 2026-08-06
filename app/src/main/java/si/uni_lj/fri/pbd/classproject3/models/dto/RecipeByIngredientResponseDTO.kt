package si.uni_lj.fri.pbd.classproject3.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeByIngredientResponseDTO(
    @SerializedName("meals")
    @Expose
    val RecipeByIngredientList: List<RecipesByIngredientDTO>? = null

)
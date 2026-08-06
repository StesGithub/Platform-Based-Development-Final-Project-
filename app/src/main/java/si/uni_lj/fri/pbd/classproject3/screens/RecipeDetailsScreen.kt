package si.uni_lj.fri.pbd.classproject3.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import si.uni_lj.fri.pbd.classproject3.ViewModels.SearchViewModel
import si.uni_lj.fri.pbd.classproject3.database.repository.RecipeRepository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailsScreenPage(
    mealId: String,
    navController: NavHostController,
    searchViewModel: SearchViewModel,
    recipeRepository: RecipeRepository
) {


    LaunchedEffect(mealId) {
        searchViewModel.searchById(mealId)
    }

    val recipeDetails by searchViewModel.recipeDetail.observeAsState()

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = { Text("Recipe Details Screen") },
                )

            }

        },
        bottomBar = {
            if (recipeDetails?.isFavorite == true) {
                Button(
                    modifier = Modifier.fillMaxWidth(),

                    onClick = { recipeDetails.let {
                        if (it != null) {
                            searchViewModel.removeFromFavourites(recipeDetails!!.idMeal!!)
                        }
                        navController.navigate("favourite")
                    } }
                ) { Text(text = "Remove From Favourites ") }
            } else {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { recipeDetails?.let { searchViewModel.addToFavourites(it) }
                        navController.navigate("search")
                }
                ) { Text(text = "Add To Favourites ") }
            }
        },

        modifier = Modifier.fillMaxSize(),

        )
    { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center,

            ) {
            LazyColumn (
                modifier = Modifier.padding(10.dp),
            ){
                item {
                    Text(
                        text = "${recipeDetails?.strMeal}",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(vertical = 10.dp)
                    )
                }
                item {
                    AsyncImage(model = recipeDetails?.strMealThumb, contentDescription = null)
                }
                item {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 5.dp)
                            .border(
                                BorderStroke(2.dp, Color.Black)

                            )
                    ) {

                        Text(
                            text = "${recipeDetails?.strInstructions}",
                            modifier = Modifier.padding(horizontal = 3.dp)
                        )
                    }

                }
                item {

                }


            }


        }


    }
}


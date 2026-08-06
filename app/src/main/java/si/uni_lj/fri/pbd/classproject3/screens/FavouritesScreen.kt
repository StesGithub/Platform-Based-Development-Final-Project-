package si.uni_lj.fri.pbd.classproject3.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import si.uni_lj.fri.pbd.classproject3.ViewModels.SearchViewModel
import si.uni_lj.fri.pbd.classproject3.database.entity.RecipeDetails
import si.uni_lj.fri.pbd.classproject3.database.repository.RecipeRepository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteDisplay(
    navController: NavHostController,
    recipeRepository: RecipeRepository,
    searchViewModel: SearchViewModel
) {


    val favourites by searchViewModel.favourites.observeAsState()

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = { Text("Favourites Screen") },
                    modifier = Modifier.statusBarsPadding()
                )

            }

        },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars

    )
    { paddingValues ->


        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {

                    if (favourites != null) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            items(favourites!!) { item ->
                                favouritesdisplayBoxes(
                                    searchViewModel,
                                    item,
                                    navController
                                )
                            }
                        }

                    } else {
                        Text(text = "favourites are empty")
                    }
                }


            }


        }
    }
}

@Composable
fun favouritesdisplayBoxes(
    searchViewModel: SearchViewModel,
    recipe: RecipeDetails,
    navController: NavHostController
) {
    Surface(
        color = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                recipe.idMeal?.let {
                    //searchViewModel.searchByIdFromDb(it)
                    navController.navigate("details/${it}")
                }
            }


    ) {
        Column {
            AsyncImage(model = recipe.strMealThumb, contentDescription = null)
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                text = "${recipe.strMeal}",

            )
        }



    }
}
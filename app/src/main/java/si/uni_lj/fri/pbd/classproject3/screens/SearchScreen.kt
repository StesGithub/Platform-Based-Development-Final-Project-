package si.uni_lj.fri.pbd.classproject3.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import si.uni_lj.fri.pbd.classproject3.ViewModels.SearchViewModel
import si.uni_lj.fri.pbd.classproject3.models.dto.RecipesByIngredientDTO


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDisplay(navController: NavHostController, searchViewModel: SearchViewModel) {


    val recipes by searchViewModel.recipes.observeAsState()
    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }
    var buttonText = remember { mutableStateOf("Select an Ingredient") }
    val searchItems = listOf("Chicken", "Beef", "Pork", "Egg", "Rice", "Potato")




    Scaffold(

        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = { Text("Search screen ") },
                    modifier = Modifier.statusBarsPadding()
                )

            }

        },
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            ExposedDropdownMenuBox(
                expanded = isDropDownExpanded.value,
                onExpandedChange = { isDropDownExpanded.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp)

            ) {

                TextField(
                    value = buttonText.value,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropDownExpanded.value) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isDropDownExpanded.value,
                    onDismissRequest = { isDropDownExpanded.value = false },
                ) {
                    searchItems.forEach { searchItem ->
                        DropdownMenuItem(

                            text = {
                                Text(
                                    searchItem,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            },

                            onClick = {
                                buttonText.value = searchItem
                                isDropDownExpanded.value = false
                                Log.d("SEARCH", "You clicked $searchItem ")
                                searchViewModel.searchByIngredient(searchItem)


                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }

            if (recipes != null) {
                LazyColumn(
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(recipes!!) { recipe ->
                        recipeDisplayBoxes(
                            searchViewModel,
                            recipe,
                            navController
                        )
                    }
                }

            }
        }
    }

}

@Composable
fun recipeDisplayBoxes(
    searchViewModel: SearchViewModel,
    recipe: RecipesByIngredientDTO,
    navController: NavHostController
) {
    Surface(
        color = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { recipe.idMeal?.let { navController.navigate("details/${it}") } }


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


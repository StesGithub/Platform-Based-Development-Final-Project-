package si.uni_lj.fri.pbd.classproject3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import si.uni_lj.fri.pbd.classproject3.ViewModels.SearchViewModel
import si.uni_lj.fri.pbd.classproject3.database.repository.RecipeRepository
import si.uni_lj.fri.pbd.classproject3.screens.FavouriteDisplay
import si.uni_lj.fri.pbd.classproject3.screens.RecipeDetailsScreenPage
import si.uni_lj.fri.pbd.classproject3.screens.SearchDisplay
import si.uni_lj.fri.pbd.classproject3.screens.SplashScreenDisplay


@Composable
fun AppNavHost(searchViewModel: SearchViewModel, recipeRepository: RecipeRepository) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable(NavigationItem.Splash.route) {
            SplashScreenDisplay(navController = navController)
        }
        composable(NavigationItem.Favourite.route) {
            FavouriteDisplay(
                navController = navController,
                recipeRepository = recipeRepository,
                searchViewModel = searchViewModel
            )
        }
        composable(NavigationItem.Search.route) {
            SearchDisplay(navController = navController, searchViewModel = searchViewModel)
        }
        composable(NavigationItem.Details.route) { getTheMealId ->
            val mealId = getTheMealId.arguments?.getString("mealId") ?: ""
            RecipeDetailsScreenPage(
                mealId,
                navController = navController,
                searchViewModel = searchViewModel,
                recipeRepository = recipeRepository
            )
        }


    }
}

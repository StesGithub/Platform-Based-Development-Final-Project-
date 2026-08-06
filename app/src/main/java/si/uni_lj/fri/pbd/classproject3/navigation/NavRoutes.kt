package si.uni_lj.fri.pbd.classproject3.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import si.uni_lj.fri.pbd.classproject3.navigation.NavigationItem.Favourite
import si.uni_lj.fri.pbd.classproject3.navigation.NavigationItem.Home
import si.uni_lj.fri.pbd.classproject3.navigation.NavigationItem.Search



val navItemsList = listOf(Favourite, Search)

sealed class NavigationItem(val route: String, val label: String, val icon: ImageVector) {

    data object Splash : NavigationItem("splash", "SplashScreen", Icons.Default.ThumbUp )
    data object Home : NavigationItem("home", "Home", Icons.Default.Home)
    data object Favourite: NavigationItem("favourite", "Favourite", Icons.Default.Favorite)
    data object Search: NavigationItem("search", "Search", Icons.Default.Search)
    data object Details : NavigationItem("details/{mealId}", "Details", Icons.Default.ThumbUp)



}
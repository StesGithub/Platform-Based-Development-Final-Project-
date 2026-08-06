package si.uni_lj.fri.pbd.classproject3.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import android.util.Log
import si.uni_lj.fri.pbd.classproject3.navigation.navItemsList


@Composable
fun BottomNavigationBar(navController: NavHostController){

    NavigationBar {
        navItemsList.forEach(){ item ->
            NavigationBarItem(
                selected = false,
                onClick = {navController.navigate(item.route)},
                icon = { Icon(imageVector = item.icon, contentDescription = item.label)}

            )
        }
    }

}


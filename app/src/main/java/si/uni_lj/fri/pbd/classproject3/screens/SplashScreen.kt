package si.uni_lj.fri.pbd.classproject3.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import si.uni_lj.fri.pbd.classproject3.R


@Composable
fun SplashScreenDisplay(navController: NavHostController) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000)
        navController.navigate("search")
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

        Box(

            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),

            ) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = "Logo"
            )
        }


    }


}
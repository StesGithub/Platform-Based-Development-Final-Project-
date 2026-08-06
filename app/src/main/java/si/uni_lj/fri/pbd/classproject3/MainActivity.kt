package si.uni_lj.fri.pbd.classproject3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import si.uni_lj.fri.pbd.classproject3.ViewModels.SearchViewModel
import si.uni_lj.fri.pbd.classproject3.ViewModels.ViewModelFactory
import si.uni_lj.fri.pbd.classproject3.models.dto.RecipeByIdResponseDTO
import si.uni_lj.fri.pbd.classproject3.navigation.AppNavHost
import si.uni_lj.fri.pbd.classproject3.rest.RestAPI
import si.uni_lj.fri.pbd.classproject3.rest.ServiceGenerator
import si.uni_lj.fri.pbd.classproject3.ui.theme.ClassProject3Theme


class MainActivity : ComponentActivity() {

    private lateinit var appModule: AppModule
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        appModule = AppModuleimpl(applicationContext)
        viewModelFactory = ViewModelFactory(appModule.recipeRepository)
        searchViewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

        setContent {
            ClassProject3Theme {
                AppNavHost(searchViewModel, recipeRepository = appModule.recipeRepository)
            }


        }
    }
}



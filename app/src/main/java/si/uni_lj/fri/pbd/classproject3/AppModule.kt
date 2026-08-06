package si.uni_lj.fri.pbd.classproject3

import android.app.Application
import android.content.Context
import si.uni_lj.fri.pbd.classproject3.database.RecipeDatabase
import si.uni_lj.fri.pbd.classproject3.database.dao.RecipeDao
import si.uni_lj.fri.pbd.classproject3.database.repository.RecipeRepository
import si.uni_lj.fri.pbd.classproject3.rest.RestAPI
import si.uni_lj.fri.pbd.classproject3.rest.ServiceGenerator

interface AppModule {
    val apiInstance: RestAPI
    val recipeRepository: RecipeRepository
    val recipeDao: RecipeDao

}

class AppModuleimpl(
    private val appContext: Context
): AppModule{
    override val apiInstance: RestAPI by lazy{
        ServiceGenerator.createService(RestAPI::class.java)
    }

    override val recipeRepository: RecipeRepository by lazy{
        RecipeRepository(appContext, apiInstance, recipeDao)
    }

    override val recipeDao: RecipeDao by lazy{
        RecipeDatabase.getDatabase(appContext).recipeDao()
    }


}
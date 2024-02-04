package com.tridya.foodrecipeblog.api.repo

import com.tridya.foodrecipeblog.api.ApiConstants
import com.tridya.foodrecipeblog.api.ApiInterface
import com.tridya.foodrecipeblog.api.response.RecipeCard
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class HomeRepository @Inject constructor(
    @Named(ApiConstants.RECIPE_API_SERVICE) private val apiInterface: ApiInterface,
    @Named(ApiConstants.MEALDB_API_SERVICE) private val mealDbApiInterface: ApiInterface,
    private val recipeDao: RecipeDao,
) {
    val getAllRecipes: Flow<List<RecipeCard>> = recipeDao.getAllRecipes()
    suspend fun getCountries() = apiInterface.getCountries()
    suspend fun getAreas() = mealDbApiInterface.getAreaList()
    suspend fun getRecipesByArea(area: String) = mealDbApiInterface.getRecipesByArea(area)
   /* fun getSelectedTask(recipeId: Int): Flow<RecipeCard> {
        return recipeDao.getSelectedRecipe(recipeId = recipeId)
    }*/

    suspend fun getNewRecipe(category: String = "Miscellaneous") =
        mealDbApiInterface.getRecipesByCategories(category = category)


    suspend fun addAllRecipe(recipes: List<RecipeCard>) {
        recipeDao.addAllRecipes(recipes = recipes)
    }

    suspend fun updateRecipe(recipe: RecipeCard) {
        recipeDao.updateRecipe(recipe = recipe)
    }

}
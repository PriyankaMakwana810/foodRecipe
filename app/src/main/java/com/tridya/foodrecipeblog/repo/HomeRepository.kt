package com.tridya.foodrecipeblog.repo

import com.tridya.foodrecipeblog.api.ApiConstants
import com.tridya.foodrecipeblog.api.ApiInterface
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class HomeRepository @Inject constructor(
    @Named(ApiConstants.MEALDB_API_SERVICE) private val mealDbApiInterface: ApiInterface,
    recipeDao: RecipeDao,
) {
    suspend fun getAreas() = mealDbApiInterface.getAreaList()
    suspend fun getRecipesByArea(area: String) = mealDbApiInterface.getRecipesByArea(area)

    val getAllSavedRecipe: Flow<List<RecipeCard>> = recipeDao.getSavedRecipes(true)

    suspend fun getNewRecipe(category: String = "Miscellaneous") =
        mealDbApiInterface.getRecipesByCategories(category = category)
}
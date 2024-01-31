package com.tridya.foodrecipeblog.api.repo

import com.tridya.foodrecipeblog.api.ApiConstants
import com.tridya.foodrecipeblog.api.ApiInterface
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.models.RecipeCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class SearchRepository @Inject constructor(
    @Named(ApiConstants.MEALDB_API_SERVICE) private val mealDbApiInterface: ApiInterface,
    private val recipeDao: RecipeDao,
) {
    val getAllRecipes: Flow<List<RecipeCard>> = recipeDao.getAllRecipes()
    suspend fun searchRecipeByName(name: String) = mealDbApiInterface.searchRecipeByName(name)
}
package com.tridya.foodrecipeblog.api.repo

import com.tridya.foodrecipeblog.api.ApiConstants
import com.tridya.foodrecipeblog.api.ApiInterface
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class CommonRepository @Inject constructor(
    @Named(ApiConstants.MEALDB_API_SERVICE)
    private val mealDbApiInterface: ApiInterface,
    private val recipeDao: RecipeDao,
) {
    val getAllRecipes: Flow<List<RecipeCard>> = recipeDao.getAllRecipes()

    val getAllSavedRecipe: Flow<List<RecipeCard>> = recipeDao.getSavedRecipes(true)

    val getAllPostedRecipe: Flow<List<RecipeCard>> = recipeDao.getPostedRecipes(true)
    suspend fun addRecipe(recipe: RecipeCard) {
        recipeDao.addRecipe(recipe = recipe)
    }
}
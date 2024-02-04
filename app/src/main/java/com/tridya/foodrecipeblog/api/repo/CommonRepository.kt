package com.tridya.foodrecipeblog.api.repo

import com.tridya.foodrecipeblog.api.ApiConstants
import com.tridya.foodrecipeblog.api.ApiInterface
import com.tridya.foodrecipeblog.api.response.RecipeCard
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.database.dao.RecipeDetailsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class CommonRepository @Inject constructor(
    @Named(ApiConstants.MEALDB_API_SERVICE) private val mealDbApiInterface: ApiInterface,
    private val recipeDao: RecipeDao,
    private val recipeDetailsDao: RecipeDetailsDao,
) {
    val getAllRecipes: Flow<List<RecipeCard>> = recipeDao.getAllRecipes()

    val getAllSavedRecipe: Flow<List<RecipeCard>> = recipeDao.getSavedRecipes(true)


}
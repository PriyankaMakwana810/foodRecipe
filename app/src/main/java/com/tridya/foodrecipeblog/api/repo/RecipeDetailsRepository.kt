package com.tridya.foodrecipeblog.api.repo

import com.tridya.foodrecipeblog.api.ApiConstants
import com.tridya.foodrecipeblog.api.ApiInterface
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import javax.inject.Inject
import javax.inject.Named

class RecipeDetailsRepository @Inject constructor(
    @Named(ApiConstants.MEALDB_API_SERVICE)
    private val mealDbApiInterface: ApiInterface,
    private val recipeDao: RecipeDao,
) {
    suspend fun getRecipeDetailsByID(recipeId: String) =
        mealDbApiInterface.getRecipeDetailsByID(recipeId = recipeId)

    fun getSelectedRecipe(recipeId: String): RecipeCard {
        return recipeDao.getSelectedRecipe(recipeId = recipeId)
    }

    suspend fun updateIsSaved(isSaved: Boolean, recipeId: String) {
        recipeDao.updateRecipeSaved(isSaved = isSaved, idMeal = recipeId)
    }

    suspend fun addRecipe(recipe: RecipeCard) {
        recipeDao.addRecipe(recipe = recipe)
    }

}
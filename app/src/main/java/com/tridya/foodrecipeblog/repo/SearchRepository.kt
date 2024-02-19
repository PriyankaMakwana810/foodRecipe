package com.tridya.foodrecipeblog.repo

import com.tridya.foodrecipeblog.api.ApiConstants
import com.tridya.foodrecipeblog.api.ApiInterface
import com.tridya.foodrecipeblog.database.dao.RecipeDao
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class SearchRepository @Inject constructor(
    @Named(ApiConstants.MEALDB_API_SERVICE) private val mealDbApiInterface: ApiInterface,
    private val recipeDao: RecipeDao,
) {
    val getAllSearchedRecipe: Flow<List<RecipeCard>> = recipeDao.getSearchedRecipes(true)
    suspend fun addRecipe(recipe: RecipeCard) {
        withContext(Dispatchers.IO) {
            val isAlreadyAdded = recipeDao.getSelectedRecipe(recipe.idMeal)
            if (isAlreadyAdded == null) {
                recipeDao.addRecipe(recipe)
            } else {
                if (!isAlreadyAdded.equals(recipe)) {
                    recipeDao.updateRecipe(recipe)
                }
            }
        }
    }

    suspend fun searchRecipeByName(name: String) =
        mealDbApiInterface.searchRecipesByName(name = name)

    suspend fun searchRecipeByFirstLetter(firstLetter: String) =
        mealDbApiInterface.searchRecipesByFirstLetter(firstLetter = firstLetter)

    suspend fun getCategoriesList() = mealDbApiInterface.getCategoriesList()
    suspend fun getIngredientList() = mealDbApiInterface.getIngredientsList()
    suspend fun getRecipeByCategories(category: String) =
        mealDbApiInterface.getRecipesByCategories(category = category)

    suspend fun getRecipeByIngredients(ingredients: String) =
        mealDbApiInterface.getRecipesByIngredients(ingredient = ingredients)

}
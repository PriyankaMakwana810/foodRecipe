package com.tridya.foodrecipeblog.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tridya.foodrecipeblog.api.response.RecipeDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDetailsDao {
    @Query("SELECT * FROM recipe_details_table ORDER BY id ASC")
    fun getAllRecipes(): Flow<List<RecipeDetails>>

    @Query("SELECT * FROM recipe_details_table WHERE strArea = :strArea")
    fun getAllRecipesByArea(strArea: String): Flow<List<RecipeDetails>>
    @Query("SELECT * FROM recipe_details_table WHERE strCategory = :strCategory")
    fun getAllRecipeByCategories(strCategory: String): Flow<List<RecipeDetails>>

    @Query("SELECT * FROM recipe_details_table WHERE idMeal=:recipeId")
    fun getSelectedRecipe(recipeId: Int): Flow<RecipeDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: RecipeDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRecipes(recipes: List<RecipeDetails>)

    @Update
    suspend fun updateRecipe(recipe: RecipeDetails)

    @Delete
    suspend fun deleteTask(recipe: RecipeDetails)

    @Query("DELETE FROM recipe_details_table")
    suspend fun deleteAllRecipes()

    @Query("UPDATE recipe_details_table Set isSaved =:isSaved WHERE idMeal = :idMeal")
    suspend fun updateRecipeSaved(isSaved: Boolean, idMeal: Int)

    @Query("SELECT * FROM recipe_details_table WHERE isSaved =:isSaved ORDER BY id DESC")
    fun getSavedRecipes(isSaved: Boolean): Flow<List<RecipeDetails>>
    @Query("SELECT * FROM recipe_details_table WHERE isSearched =:isSearched ORDER BY id DESC")
    fun getSearchedRecipes(isSearched: Boolean): Flow<List<RecipeDetails>>

}
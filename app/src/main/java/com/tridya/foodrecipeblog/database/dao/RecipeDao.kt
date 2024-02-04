package com.tridya.foodrecipeblog.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tridya.foodrecipeblog.api.response.RecipeCard
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe_table ORDER BY id ASC")
    fun getAllRecipes(): Flow<List<RecipeCard>>

    @Query("SELECT * FROM recipe_table WHERE idMeal=:recipeId")
    fun getSelectedRecipe(recipeId: String): RecipeCard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: RecipeCard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRecipes(recipes: List<RecipeCard>)

    @Update
    suspend fun updateRecipe(recipe: RecipeCard)

    @Delete
    suspend fun deleteTask(recipe: RecipeCard)

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAllRecipes()

    @Query("UPDATE recipe_table Set isSaved =:isSaved WHERE idMeal = :idMeal")
    suspend fun updateRecipeSaved(isSaved: Boolean, idMeal: Int)

    @Query("SELECT * FROM recipe_table WHERE isSaved =:isSaved ORDER BY id DESC")
    fun getSavedRecipes(isSaved: Boolean): Flow<List<RecipeCard>>

    @Query("SELECT * FROM recipe_table WHERE isSearched =:isSearched ORDER BY id DESC")
    fun getSearchedRecipes(isSearched: Boolean): Flow<List<RecipeCard>>


}
package com.tridya.foodrecipeblog.viewModels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.RecipeDetailsRepository
import com.tridya.foodrecipeblog.api.response.RecipeCard
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val repository: RecipeDetailsRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel() {
    private val _recipeDetails = MutableStateFlow<ApiState<RecipeCard>>(ApiState.Loading)
    val recipeDetails: StateFlow<ApiState<RecipeCard>> = _recipeDetails

    private val _selectedRecipe =
        MutableStateFlow<RecipeCard?>(null)
    val selectedRecipe: StateFlow<RecipeCard?> = _selectedRecipe


    fun getRecipeDetailsByID(recipeId: String) {
        viewModelScope.launch {
            try {
                val response = repository.getRecipeDetailsByID(recipeId)
                _recipeDetails.value = ApiState.Success(response.meals.first())

            } catch (e: Exception) {
                _recipeDetails.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

    fun getRecipeDetailsFromDB(recipeId: String) {
        try {
            viewModelScope.launch {
                _selectedRecipe.value = repository.getSelectedRecipe(recipeId)
            }
        } catch (e: Exception) {
            _selectedRecipe.value = null
        }
    }

    fun shareRecipeLink(subject: String, text: String, context: android.content.Context) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(sendIntent)
        }
    }
}
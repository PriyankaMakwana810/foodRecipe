package com.tridya.foodrecipeblog.viewModels

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
}
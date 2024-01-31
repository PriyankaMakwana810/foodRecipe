package com.tridya.foodrecipeblog.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.SearchRepository
import com.tridya.foodrecipeblog.api.response.RecipeDetails
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel() {
    private val _allRecipes =
        MutableStateFlow<List<RecipeCard>>(emptyList())
    val allRecipes: StateFlow<List<RecipeCard>> = _allRecipes

    private val _recipesByName = MutableStateFlow<ApiState<List<RecipeDetails>>>(ApiState.Loading)
    val recipesByName: StateFlow<ApiState<List<RecipeDetails>>> = _recipesByName

    init {
        getAllStoredRecipes()
    }

    fun getAllStoredRecipes() {
        try {
            viewModelScope.launch {
                repository.getAllRecipes.collect {
                    _allRecipes.value = it
                }
            }
        } catch (e: Exception) {
            _allRecipes.value = emptyList()
        }
    }

    fun getRecipesByName(searchName: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchRecipeByName(searchName)
                _recipesByName.value = ApiState.Success(response.meals)

            } catch (e: Exception) {
                _recipesByName.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

}
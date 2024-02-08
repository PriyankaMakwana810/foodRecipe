package com.tridya.foodrecipeblog.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.HomeRepository
import com.tridya.foodrecipeblog.api.response.Areas
import com.tridya.foodrecipeblog.api.response.ResponseOfRecipes
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel() {

    private val _areas = MutableStateFlow<ApiState<List<Areas>>>(ApiState.Loading)
    val areas: StateFlow<ApiState<List<Areas>>> = _areas

    private val _recipesByArea =
        MutableStateFlow<ApiState<List<ResponseOfRecipes>>>(ApiState.Loading)
    val recipesByArea: StateFlow<ApiState<List<ResponseOfRecipes>>> = _recipesByArea

    private val _newRecipes = MutableStateFlow<ApiState<List<ResponseOfRecipes>>>(ApiState.Loading)
    val newRecipes: StateFlow<ApiState<List<ResponseOfRecipes>>> = _newRecipes

    private val _loaded: MutableState<Boolean> = mutableStateOf(false)
    val loaded: State<Boolean> get() = _loaded

    fun getAreas() {
        viewModelScope.launch {
            try {
                val response = repository.getAreas()
                _areas.value = ApiState.Success(response.meals)

            } catch (e: Exception) {
                _areas.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

    fun getRecipeByArea(area: String) {
        viewModelScope.launch {
            try {
                _loaded.value = true
                val response = repository.getRecipesByArea(area)
                _recipesByArea.value = ApiState.Success(response.meals)
            } catch (e: Exception) {
                _recipesByArea.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

    fun getNewRecipe() {
        viewModelScope.launch {
            try {
                val response = repository.getNewRecipe()
                _newRecipes.value = ApiState.Success(response.meals)

            } catch (e: Exception) {
                _newRecipes.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

}


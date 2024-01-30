package com.tridya.foodrecipeblog.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.HomeRepository
import com.tridya.foodrecipeblog.api.response.Meal
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel() {
    private val _countries = mutableStateOf<ApiState<List<String>>>(ApiState.Loading)
    val countries: State<ApiState<List<String>>> = _countries

    private val _areas = mutableStateOf<ApiState<List<Meal>>>(ApiState.Loading)
    val areas: MutableState<ApiState<List<Meal>>> = _areas

    private val _recipesByArea = mutableStateOf<ApiState<List<RecipeCard>>>(ApiState.Loading)
    val recipesByArea: State<ApiState<List<RecipeCard>>> = _recipesByArea

    private val _newRecipes = mutableStateOf<ApiState<List<RecipeCard>>>(ApiState.Loading)
    val newRecipes: State<ApiState<List<RecipeCard>>> = _newRecipes

    fun getCountries() {
        viewModelScope.launch {
            try {
                val response = repository.getCountries()
                _countries.value = ApiState.Success(response.cuisines.data)

            } catch (e: Exception) {
                _countries.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

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
                val response = repository.getRecipesByArea(area)
                _recipesByArea.value = ApiState.Success(response.meals)

            } catch (e: Exception) {
                _recipesByArea.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

    fun getNewRecipe(area: String) {
        viewModelScope.launch {
            try {
                val response = repository.getRecipesByArea(area)
                _newRecipes.value = ApiState.Success(response.meals)

            } catch (e: Exception) {
                _newRecipes.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

}

fun getSnacks(): List<RecipeCard> = recipesByCountry

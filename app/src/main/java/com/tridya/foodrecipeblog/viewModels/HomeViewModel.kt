package com.tridya.foodrecipeblog.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.HomeRepository
import com.tridya.foodrecipeblog.api.response.Meal
import com.tridya.foodrecipeblog.api.response.ResponseOfRecipes
import com.tridya.foodrecipeblog.models.RecipeCard
import com.tridya.foodrecipeblog.models.recipesByCountry
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private var _allRecipes =
        MutableStateFlow<List<RecipeCard>>(emptyList())
    val allRecipes: StateFlow<List<RecipeCard>> = _allRecipes

    private val _countries = MutableStateFlow<ApiState<List<String>>>(ApiState.Loading)
    val countries: StateFlow<ApiState<List<String>>> = _countries

    private val _areas = MutableStateFlow<ApiState<List<Meal>>>(ApiState.Loading)
    val areas: StateFlow<ApiState<List<Meal>>> = _areas

    private val _recipesByArea = MutableStateFlow<ApiState<List<ResponseOfRecipes>>>(ApiState.Loading)
    val recipesByArea: StateFlow<ApiState<List<ResponseOfRecipes>>> = _recipesByArea

    private val _newRecipes = MutableStateFlow<ApiState<List<ResponseOfRecipes>>>(ApiState.Loading)
    val newRecipes: StateFlow<ApiState<List<ResponseOfRecipes>>> = _newRecipes

    init {
        getAllRecipes()
    }

    private fun getAllRecipes(){
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

    fun addAllRecipes(recipes: List<RecipeCard>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAllRecipe(recipes)
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
                if (!sharedPreferences.getBoolean("isAddedToDb")){
                    val response = repository.getRecipesByArea(area)
                    _recipesByArea.value = ApiState.Success(response.meals)
                }else{
                    _recipesByArea.value = ApiState.Success(emptyList())
                }


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

package com.tridya.foodrecipeblog.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.SearchRepository
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
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel() {
    private val _allSearchedRecipes =
        MutableStateFlow<List<RecipeCard>>(emptyList())
    val allSearchedRecipes: StateFlow<List<RecipeCard>> = _allSearchedRecipes

    private val _recipesByName = MutableStateFlow<ApiState<List<RecipeCard>>>(ApiState.Loading)
    val recipesByName: StateFlow<ApiState<List<RecipeCard>>> = _recipesByName

    private val _categoryList = MutableStateFlow<ApiState<List<String>>>(ApiState.Loading)
    val categoryList: StateFlow<ApiState<List<String>>> = _categoryList

    private val _ingredientList = MutableStateFlow<ApiState<List<String>>>(ApiState.Loading)
    val ingredientList: StateFlow<ApiState<List<String>>> = _ingredientList

    init {
        getAllStoredRecipes()
    }

    private fun getAllStoredRecipes() {
        try {
            viewModelScope.launch {
                repository.getAllSearchedRecipe.collect {
                    _allSearchedRecipes.value = it
                }
            }
        } catch (e: Exception) {
            _allSearchedRecipes.value = emptyList()
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

    fun getCategoryList() {
        viewModelScope.launch {
            try {
                val response = repository.getCategoriesList()
//                categories = response.meals
                _categoryList.value = ApiState.Success(response.meals.map { it.strCategory })
                Log.e("TAG", "getCategoryList: ${response.meals.map { it.strCategory }}")
            } catch (e: Exception) {
                _categoryList.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

    fun getIngredientList() {
        viewModelScope.launch {
            try {
                val response = repository.getIngredientList()
                _ingredientList.value =
                    ApiState.Success(response.meals.map { it.strIngredient }.subList(0, 10))
            } catch (e: Exception) {
                _ingredientList.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

    fun addRecipe(recipe: RecipeCard) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
        }
    }

    fun getRecipesByFirstLetter(firstLetter: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchRecipeByName(firstLetter)
                _recipesByName.value = ApiState.Success(response.meals)

            } catch (e: Exception) {
                _recipesByName.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

}
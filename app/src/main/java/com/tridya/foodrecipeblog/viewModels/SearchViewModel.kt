package com.tridya.foodrecipeblog.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.SearchRepository
import com.tridya.foodrecipeblog.api.response.Categories
import com.tridya.foodrecipeblog.api.response.Ingredients
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
    private val _allRecipes =
        MutableStateFlow<List<RecipeCard>>(emptyList())
    val allRecipes: StateFlow<List<RecipeCard>> = _allRecipes

    var categories: List<Categories> = emptyList()
    var ingredients: List<Ingredients> = emptyList()

    private val _recipesByName = MutableStateFlow<ApiState<List<RecipeCard>>>(ApiState.Loading)
    val recipesByName: StateFlow<ApiState<List<RecipeCard>>> = _recipesByName

    private val _categoryList = MutableStateFlow<ApiState<List<Categories>>>(ApiState.Loading)
    val categoryList: StateFlow<ApiState<List<Categories>>> = _categoryList

    private val _ingredientList = MutableStateFlow<ApiState<List<Ingredients>>>(ApiState.Loading)
    val ingredientList: StateFlow<ApiState<List<Ingredients>>> = _ingredientList

//    private var _categoryList: MutableState<List<Categories>> = mutableStateOf(listOf())
//    val categoryList: State<List<Categories>> get() = _categoryList

    init {
        getAllStoredRecipes()
//        getIngredientList()
//        getCategoryList()
    }

    private fun getAllStoredRecipes() {
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

    fun getCategoryList() {
        viewModelScope.launch {
            try {
                val response = repository.getCategoriesList()
                categories = response.meals
                _categoryList.value = ApiState.Success(response.meals)
            } catch (e: Exception) {
                _categoryList.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

    fun getIngredientList() {
        viewModelScope.launch {
            try {
                val response = repository.getIngredientList()
                ingredients = response.meals
                _ingredientList.value = ApiState.Success(response.meals)
            } catch (e: Exception) {
                _ingredientList.value = ApiState.Error("API Error: ${e.message}")
            }
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
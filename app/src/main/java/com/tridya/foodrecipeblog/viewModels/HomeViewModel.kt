package com.tridya.foodrecipeblog.viewModels

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.HomeRepository
import com.tridya.foodrecipeblog.api.response.Areas
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import com.tridya.foodrecipeblog.utils.isInternetAvailable
import com.tridya.foodrecipeblog.utils.showShortToast
import com.tridya.foodrecipeblog.utils.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
    private val context: Context,
) : ViewModel() {

    private val _areas = MutableStateFlow<ApiState<List<Areas>>>(ApiState.Loading)
    val areas: StateFlow<ApiState<List<Areas>>> = _areas

    private val _allSavedRecipes =
        MutableStateFlow<List<RecipeCard>>(emptyList())
    val allSavedRecipes: StateFlow<List<RecipeCard>> = _allSavedRecipes

    private val _recipesByArea =
        MutableStateFlow<ApiState<List<RecipeCard>>>(ApiState.Loading)
    val recipesByArea: StateFlow<ApiState<List<RecipeCard>>> = _recipesByArea

    private val _newRecipes = MutableStateFlow<ApiState<List<RecipeCard>>>(ApiState.Loading)
    val newRecipes: StateFlow<ApiState<List<RecipeCard>>> = _newRecipes

    private val _loaded: MutableState<Boolean> = mutableStateOf(false)
    val loaded: State<Boolean> get() = _loaded

    init {
        getAllSavedRecipes()
    }

    fun getAreas() {
        if (isInternetAvailable(context = context)) {
            viewModelScope.launch {
                try {
                    val response = repository.getAreas()
                    _areas.value = ApiState.Success(response.meals)

                } catch (e: Exception) {
                    _areas.value = ApiState.Error("API Error: ${e.message}")
                }
            }
        } else {
            _areas.value = ApiState.Error(context.getString(R.string.no_internet_connection))
            showShortToast(context, context.getString(R.string.no_internet_connection))
        }
    }

    fun getAllSavedRecipes() {
        try {
            viewModelScope.launch {
                repository.getAllSavedRecipe.collect {
                    _allSavedRecipes.value = it
                }
            }
        } catch (e: Exception) {
            _allSavedRecipes.value = emptyList()
        }
    }

    fun getRecipeByArea(area: String) {
        if (isInternetAvailable(context)) {
            viewModelScope.launch {
                try {
                    _loaded.value = true
                    val response = repository.getRecipesByArea(area)
                    val actualResponse = response.meals.map {
                        it.toEntity(
                            area = area
                        )
                    }
                    _recipesByArea.value = ApiState.Success(actualResponse)
                } catch (e: Exception) {
                    _recipesByArea.value = ApiState.Error("API Error: ${e.message}")
                }
            }
        } else {
            _recipesByArea.value =
                ApiState.Error(context.getString(R.string.no_internet_connection))
            showShortToast(context, context.getString(R.string.no_internet_connection))
        }

    }

    fun getNewRecipe() {
        viewModelScope.launch {
            try {
                val response = repository.getNewRecipe()
                val actualResponse = response.meals.map {
                    it.toEntity(
                        category = "Miscellaneous"
                    )
                }
                _newRecipes.value = ApiState.Success(actualResponse)

            } catch (e: Exception) {
                _newRecipes.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }

}


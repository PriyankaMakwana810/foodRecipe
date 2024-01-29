package com.tridya.foodrecipeblog.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.HomeRepository
import com.tridya.foodrecipeblog.api.response.Cuisines
import com.tridya.foodrecipeblog.api.response.CuisinesResponse
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
    val areas: State<ApiState<List<Meal>>> = _areas


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


}

fun getSnacks(): List<RecipeCard> = recipesByCountry

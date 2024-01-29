package com.tridya.foodrecipeblog.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.api.ApiState
import com.tridya.foodrecipeblog.api.repo.HomeRepository
import com.tridya.foodrecipeblog.api.response.CuisinesResponse
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
    private val _countries = mutableStateOf<ApiState<CuisinesResponse>>(ApiState.Loading)
    val countries: State<ApiState<CuisinesResponse>> = _countries

    fun getCountries() {
        viewModelScope.launch {
            try {
                val response = repository.getCountries()
                _countries.value = ApiState.Success(response)
            } catch (e: Exception) {
                _countries.value = ApiState.Error("API Error: ${e.message}")
            }
        }
    }
}

fun getSnacks(): List<RecipeCard> = recipesByCountry

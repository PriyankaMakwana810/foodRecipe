package com.tridya.foodrecipeblog.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridya.foodrecipeblog.repo.CommonRepository
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: CommonRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
) : ViewModel() {
    private val _allPostedRecipes =
        MutableStateFlow<List<RecipeCard>>(emptyList())
    val allPostedRecipes: StateFlow<List<RecipeCard>> = _allPostedRecipes

    init {
        getAllSavedRecipes()
    }

    private fun getAllSavedRecipes() {
        try {
            viewModelScope.launch {
                repository.getAllPostedRecipe.collect {
                    _allPostedRecipes.value = it
                }
            }
        } catch (e: Exception) {
            _allPostedRecipes.value = emptyList()
        }
    }

}
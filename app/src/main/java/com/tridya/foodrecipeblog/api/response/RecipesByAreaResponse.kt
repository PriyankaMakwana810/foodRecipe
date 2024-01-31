package com.tridya.foodrecipeblog.api.response

import com.tridya.foodrecipeblog.models.RecipeCard

data class RecipesByAreaResponse(
    val meals: List<ResponseOfRecipes>
)

data class ResponseOfRecipes(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
)
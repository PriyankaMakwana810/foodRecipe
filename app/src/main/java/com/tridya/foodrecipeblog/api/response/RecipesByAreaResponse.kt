package com.tridya.foodrecipeblog.api.response

data class RecipesByAreaResponse(
    val meals: List<ResponseOfRecipes>,
)

data class ResponseOfRecipes(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
)
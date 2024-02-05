package com.tridya.foodrecipeblog.api.response

import com.tridya.foodrecipeblog.database.tables.RecipeCard

data class RecipeDetailsResponse(
    val meals: List<RecipeCard>,
)


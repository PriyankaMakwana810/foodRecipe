package com.tridya.foodrecipeblog.api.response

import com.tridya.foodrecipeblog.models.RecipeCard

data class RecipesByAreaResponse(
    val meals: List<RecipeCard>
)

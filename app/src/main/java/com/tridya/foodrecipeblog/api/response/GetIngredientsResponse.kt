package com.tridya.foodrecipeblog.api.response

data class GetIngredientsResponse(
    val meals: List<Ingredients>
)
data class Ingredients(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String,
    val strType: String
)
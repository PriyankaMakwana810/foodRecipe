package com.tridya.foodrecipeblog.api.response

data class CuisinesResponse(
    val cuisines: Cuisines,
    val version: String,
)

data class Cuisines(
    val data: List<String>,
)
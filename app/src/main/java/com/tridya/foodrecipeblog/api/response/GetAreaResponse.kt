package com.tridya.foodrecipeblog.api.response

data class GetAreaResponse(
    val meals: List<Meal>
)
data class Meal(
    val strArea: String
)
package com.tridya.foodrecipeblog.api.response

data class GetAreaResponse(
    val meals: List<Areas>
)
data class Areas(
    val strArea: String
)
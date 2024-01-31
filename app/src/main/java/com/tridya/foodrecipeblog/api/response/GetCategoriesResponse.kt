package com.tridya.foodrecipeblog.api.response

data class GetCategoriesResponse(
    val meals: List<Categories>
)
data class Categories(
    val strCategories: String
)
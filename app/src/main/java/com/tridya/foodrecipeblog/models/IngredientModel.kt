package com.tridya.foodrecipeblog.models

data class IngredientModel(
    val itemId: Int = 0,
    val itemImage: String,
    val itemName: String,
    val itemWeight: Double? = null,
)

data class ProcedureModel(
    val step: Int,
    val stepDetail: String,
)
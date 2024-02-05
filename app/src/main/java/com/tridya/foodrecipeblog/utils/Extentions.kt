package com.tridya.foodrecipeblog.utils

import android.content.Context
import android.widget.Toast
import com.tridya.foodrecipeblog.api.response.ResponseOfRecipes
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.database.tables.RecipeDetails
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toToast(context: Context, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, length).show()
}

fun showShortToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

fun ResponseOfRecipes.toEntity(area: String = "", category: String = ""): RecipeCard {
    val randomName = generateRandomName()
    return RecipeCard(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb,
        timeToCook = generateRandomTimeToCook(),
        tagline = "tagline",
        isSaved = false,
        isSearched = false,
        ratings = generateRandomRating(),
        postedBy = randomName,
        userProfilePhoto = generateUserProfilePhotoForUser(randomName),
        strCategory = category,
        strArea = area
    )
}

fun RecipeCard.toEntity(): RecipeDetails {
    return RecipeDetails(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb,
        timeToCook = timeToCook,
        tagline = tagline,
        isSaved = isSaved,
        isSearched = isSearched,
        ratings = ratings,
        postedBy = postedBy,
        userProfilePhoto = userProfilePhoto
    )
}

fun convertToRecipeCard(recipeDetails: RecipeDetails): RecipeCard {
    return RecipeCard(
        id = recipeDetails.id,
        idMeal = recipeDetails.idMeal,
        strMeal = recipeDetails.strMeal,
        timeToCook = recipeDetails.timeToCook,
        tagline = recipeDetails.tagline,
        isSaved = recipeDetails.isSaved,
        // ... (map other properties accordingly)
    )
}

fun convertListToRecipeCardList(recipeDetailsList: List<RecipeDetails>): List<RecipeCard> {
    return recipeDetailsList.map { convertToRecipeCard(it) }
}
/*
fun RecipeDetails.toEntity(): RecipeCard {
    val randomName = generateRandomName()
    return RecipeCard(
        idMeal = idMeal,
        strMeal = strMeal,
        strMealThumb = strMealThumb,
        timeToCook = generateRandomTimeToCook(),
        tagline = "tagline",
        isSaved = false,
        ratings = generateRandomRating(),
        postedBy = randomName,
        userProfilePhoto = generateUserProfilePhotoForUser(randomName)
//        userProfilePhoto = "https://images.unsplash.com/photo-1705582033498-e7384d494759?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        // Add additional fields and their default values
    )
}*/

fun generateRandomRating(): Float {
    return (1..5).random().toFloat()
}

fun generateRandomTimeToCook(): Long {
    return (1..30).random().toLong()
}

fun generateRandomName(): String {
    val longNames = listOf(
        "Bella Throne", "Christopher Oshana", "Kyle Austin", "Jeniffern Wilson", "Kate Johnson",
    )
    return longNames.random()
}

fun generateUserProfilePhotoForUser(username: String): String {
    val userToPhotoMap = mapOf(
        "Bella Throne" to "https://unsplash.com/photos/closeup-photography-of-woman-smiling-mEZ3PoFGs_k",
        "Christopher Oshana" to "https://unsplash.com/photos/man-taking-selfie-tidSLv-UaNs",
        "Kyle Austin" to "https://unsplash.com/photos/man-in-black-button-up-shirt-ZHvM3XIOHoE",
        "Jeniffern Wilson" to "https://unsplash.com/photos/woman-in-black-and-white-checkered-hoodie-OFAbs8e8Zic",
        "Kate Johnson" to "https://unsplash.com/photos/woman-in-white-off-shoulder-dress-uR51HXLO7G0",
        // Add more mappings as needed
    )
    return userToPhotoMap[username] ?: "https://example.com/default.jpg"
}

fun convertDateTimeToLong(dateTime: String): Long? {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US)
    return try {
        val inputDate = dateFormat.parse(dateTime)
        inputDate?.time
    } catch (e: Exception) {
        null
    }
}
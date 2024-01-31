package com.tridya.foodrecipeblog.utils

import android.content.Context
import android.widget.Toast
import com.tridya.foodrecipeblog.api.response.ResponseOfRecipes
import com.tridya.foodrecipeblog.models.RecipeCard
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toToast(context: Context, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, length).show()
}

fun showShortToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

fun ResponseOfRecipes.toEntity(): RecipeCard {
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
}

private fun generateRandomRating(): Float {
    return (1..5).random().toFloat()
}

private fun generateRandomTimeToCook(): Long {
    return (1..30).random().toLong()
}

private fun generateRandomName(): String {
    val longNames = listOf(
        "Bella Throne", "Christopher Oshana", "Kyle Austin", "Jeniffern Wilson", "Kate Johnson",
    )
    return longNames.random()
}

private fun generateUserProfilePhotoForUser(username: String): String {
    val userToPhotoMap = mapOf(
        "Bella Throne" to "https://bit.ly/3S7gpTv",
        "Christopher Oshana" to "https://bit.ly/4b2Cmf7",
        "Kyle Austin" to "https://bit.ly/3Hwof40",
        "Jeniffern Wilson" to "https://bit.ly/3vTQGWW",
        "Kate Johnson" to "https://bit.ly/3vUnu21",
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
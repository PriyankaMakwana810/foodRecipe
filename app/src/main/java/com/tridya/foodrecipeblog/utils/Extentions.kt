package com.tridya.foodrecipeblog.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
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
        "Bella Throne" to "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "Christopher Oshana" to "https://images.unsplash.com/photo-1529068755536-a5ade0dcb4e8?q=80&w=1781&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "Kyle Austin" to "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "Jeniffern Wilson" to "https://images.unsplash.com/photo-1612427404252-f424ef7a7cf5?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "Kate Johnson" to "https://images.unsplash.com/photo-1609505848912-b7c3b8b4beda?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        // Add more mappings as needed
    )
    return userToPhotoMap[username]
        ?: "https://images.unsplash.com/photo-1609505848912-b7c3b8b4beda?q=80&w=1965&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
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


fun isInternetAvailable(context: Context): Boolean {
    val result: Boolean
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
    return result
}

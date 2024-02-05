package com.tridya.foodrecipeblog.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tridya.foodrecipeblog.database.tables.randomName
import com.tridya.foodrecipeblog.utils.generateRandomRating
import com.tridya.foodrecipeblog.utils.generateRandomTimeToCook
import com.tridya.foodrecipeblog.utils.generateUserProfilePhotoForUser

@Entity(tableName = "recipe_details_table")
data class RecipeDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idMeal: String = "",
    var strMeal: String = "",
    val timeToCook: Long = generateRandomTimeToCook(),
    val tagline: String = "tagline",
    val isSaved: Boolean = false,
    val isSearched: Boolean = false,
    val ratings: Float = generateRandomRating(),
    var postedBy: String = randomName,
    val userProfilePhoto: String = generateUserProfilePhotoForUser(randomName),
    var strCategory: String? = null,
    var strArea: String? = null,
    var strMealThumb: String? = null,
)
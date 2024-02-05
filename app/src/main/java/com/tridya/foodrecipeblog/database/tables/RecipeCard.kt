package com.tridya.foodrecipeblog.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tridya.foodrecipeblog.utils.generateRandomName
import com.tridya.foodrecipeblog.utils.generateRandomRating
import com.tridya.foodrecipeblog.utils.generateRandomTimeToCook
import com.tridya.foodrecipeblog.utils.generateUserProfilePhotoForUser

val randomName = generateRandomName()

@Entity(tableName = "recipe_table")
data class RecipeCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var idMeal: String = "",
    var strMeal: String = "",
    val timeToCook: Long = generateRandomTimeToCook(),
    val tagline: String = "tagline",
    val isSaved: Boolean = false,
    var isSearched: Boolean = false,
    val ratings: Float = generateRandomRating(),
    var postedBy: String = randomName,
    val userProfilePhoto: String = generateUserProfilePhotoForUser(randomName),
    var strDrinkAlternate: String? = null,
    var strCategory: String? = null,
    var strArea: String? = null,
    var strInstructions: String? = null,
    var strMealThumb: String? = null,
    var strTags: String? = null,
    var strYoutube: String? = null,
    var strIngredient1: String? = null,
    var strIngredient2: String? = null,
    var strIngredient3: String? = null,
    var strIngredient4: String? = null,
    var strIngredient5: String? = null,
    var strIngredient6: String? = null,
    var strIngredient7: String? = null,
    var strIngredient8: String? = null,
    var strIngredient9: String? = null,
    var strIngredient10: String? = null,
    var strIngredient11: String? = null,
    var strIngredient12: String? = null,
    var strIngredient13: String? = null,
    var strIngredient14: String? = null,
    var strIngredient15: String? = null,
    var strIngredient16: String? = null,
    var strIngredient17: String? = null,
    var strIngredient18: String? = null,
    var strIngredient19: String? = null,
    var strIngredient20: String? = null,
    var strMeasure1: String? = null,
    var strMeasure2: String? = null,
    var strMeasure3: String? = null,
    var strMeasure4: String? = null,
    var strMeasure5: String? = null,
    var strMeasure6: String? = null,
    var strMeasure7: String? = null,
    var strMeasure8: String? = null,
    var strMeasure9: String? = null,
    var strMeasure10: String? = null,
    var strMeasure11: String? = null,
    var strMeasure12: String? = null,
    var strMeasure13: String? = null,
    var strMeasure14: String? = null,
    var strMeasure15: String? = null,
    var strMeasure16: String? = null,
    var strMeasure17: String? = null,
    var strMeasure18: String? = null,
    var strMeasure19: String? = null,
    var strMeasure20: String? = null,
    var strSource: String? = null,
    var strImageSource: String? = null,
    var strCreativeCommonsConfirmed: String? = null,
    var dateModified: String? = null,
) {
    fun getIngredientsList(): List<String?> {
        return listOf(
            strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
            strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
            strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
            strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20
        ).filter { !it.isNullOrBlank() }
            .map { it ?: "" }
    }

    fun getMeasuresList(): List<String?> {
        return listOf(
            strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5,
            strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
            strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15,
            strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20
        ).filter { !it.isNullOrBlank() }
            .map { it ?: "" }
    }
}

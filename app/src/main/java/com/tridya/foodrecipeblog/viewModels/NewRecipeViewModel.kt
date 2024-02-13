package com.tridya.foodrecipeblog.viewModels

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tridya.foodrecipeblog.R
import com.tridya.foodrecipeblog.api.repo.CommonRepository
import com.tridya.foodrecipeblog.database.tables.RecipeCard
import com.tridya.foodrecipeblog.navigation.Screen
import com.tridya.foodrecipeblog.utils.Constants
import com.tridya.foodrecipeblog.utils.PrefUtils
import com.tridya.foodrecipeblog.utils.showShortToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class NewRecipeViewModel @Inject constructor(
    private val repository: CommonRepository,
    @Named(Constants.SHARED_COMMON) val sharedPreferences: PrefUtils,
    private val context: Context,
) : ViewModel() {
    val recipeNameState: MutableState<String> = mutableStateOf("")
    val categoryState: MutableState<String> = mutableStateOf("")
    val timeToCookState: MutableState<String> = mutableStateOf("")
    val procedureState: MutableState<String> = mutableStateOf("")
    /*
        var ingredient: MutableState<String> = mutableStateOf("")
        var measurement: MutableState<String> = mutableStateOf("")
        var photoUri: MutableState<Uri?> = mutableStateOf(null)
    */


    //    var ingredientsList by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    var area: MutableState<String> = mutableStateOf("")
    var source: MutableState<String> = mutableStateOf("")
    var youtubeLink: MutableState<String> = mutableStateOf("")

    // Maintain a list of ingredient and measurement pairs
    val ingredientMeasurementPairs: MutableState<List<Pair<String, String>>> =
        mutableStateOf(listOf("" to ""))

    fun addIngredientMeasurementPair() {
        val currentPairs = ingredientMeasurementPairs.value

        // Check if the last pair is empty or not
        val lastPair = currentPairs.lastOrNull()
        if (lastPair != null && (lastPair.first.isEmpty() || lastPair.second.isEmpty())) {
            showShortToast(context, "Please fill the existing ingredient and measurement fields")
        } else {
            val updatedList = currentPairs.toMutableList()
            updatedList.add("" to "")
            ingredientMeasurementPairs.value = updatedList
        }
    }

    fun addRecipe(recipe: RecipeCard) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
        }
    }

    fun validateAndSaveRecipe(navController: NavController, photoUri: Uri) {
        val recipeName = recipeNameState.value
        val category = categoryState.value
        val timeToCook = timeToCookState.value
        val procedure = procedureState.value
        val ingredients = ingredientMeasurementPairs.value
        val area = area.value
        val source = source.value
        val youtubeLink = youtubeLink.value

        fun generateUniqueMealId(): String {
            val uuid = UUID.randomUUID().toString().replace("-", "")
            val randomNumber = Random.nextInt(1000, 10000) // Adjust range as needed
            return "$uuid$randomNumber"
        }

        if (recipeName.isNotEmpty() && category.isNotEmpty() && timeToCook.isNotEmpty() && procedure.isNotEmpty()
            && ingredients.isNotEmpty() && ingredients.all { it.first.isNotEmpty() && it.second.isNotEmpty() }
        ) {
            val ingredientList = mutableListOf<String>()
            val measurementsList = mutableListOf<String>()

            for ((ingredient, measurement) in ingredientMeasurementPairs.value) {
                if (ingredient.isNotEmpty() && measurement.isNotEmpty()) {
                    ingredientList.add(ingredient)
                    measurementsList.add(measurement)
                }
            }

            val newRecipe = RecipeCard(
                id = 0,
                idMeal = generateUniqueMealId(),
                isAddedByUser = true,
                strMeal = recipeName,
                timeToCook = timeToCook.toLong(),
                tagline = "",
                isSaved = false,
                isSearched = false,
                ratings = 3f,
                postedBy = sharedPreferences.user?.userName!!,
                userProfilePhoto = sharedPreferences.user?.profilePicPath!!,
                strCategory = category,
                strArea = area,
                strInstructions = procedure,
                strMealThumb = photoUri.toString(),
                strYoutube = youtubeLink,
                strIngredient1 = ingredientList.getOrNull(0),
                strIngredient2 = ingredientList.getOrNull(1),
                strIngredient3 = ingredientList.getOrNull(2),
                strIngredient4 = ingredientList.getOrNull(3),
                strIngredient5 = ingredientList.getOrNull(4),
                strIngredient6 = ingredientList.getOrNull(5),
                strIngredient7 = ingredientList.getOrNull(6),
                strIngredient8 = ingredientList.getOrNull(7),
                strIngredient9 = ingredientList.getOrNull(8),
                strIngredient10 = ingredientList.getOrNull(9),
                strIngredient11 = ingredientList.getOrNull(10),
                strIngredient12 = ingredientList.getOrNull(11),
                strIngredient13 = ingredientList.getOrNull(12),
                strIngredient14 = ingredientList.getOrNull(13),
                strIngredient15 = ingredientList.getOrNull(14),
                strIngredient16 = ingredientList.getOrNull(15),
                strIngredient17 = ingredientList.getOrNull(16),
                strIngredient18 = ingredientList.getOrNull(17),
                strIngredient19 = ingredientList.getOrNull(18),
                strIngredient20 = ingredientList.getOrNull(19),
                strMeasure1 = measurementsList.getOrNull(0),
                strMeasure2 = measurementsList.getOrNull(1),
                strMeasure3 = measurementsList.getOrNull(2),
                strMeasure4 = measurementsList.getOrNull(3),
                strMeasure5 = measurementsList.getOrNull(4),
                strMeasure6 = measurementsList.getOrNull(5),
                strMeasure7 = measurementsList.getOrNull(6),
                strMeasure8 = measurementsList.getOrNull(7),
                strMeasure9 = measurementsList.getOrNull(8),
                strMeasure10 = measurementsList.getOrNull(9),
                strMeasure11 = measurementsList.getOrNull(10),
                strMeasure12 = measurementsList.getOrNull(11),
                strMeasure13 = measurementsList.getOrNull(12),
                strMeasure14 = measurementsList.getOrNull(13),
                strMeasure15 = measurementsList.getOrNull(14),
                strMeasure16 = measurementsList.getOrNull(15),
                strMeasure17 = measurementsList.getOrNull(16),
                strMeasure18 = measurementsList.getOrNull(17),
                strMeasure19 = measurementsList.getOrNull(18),
                strMeasure20 = measurementsList.getOrNull(19),
                strSource = source,
                dateModified = System.currentTimeMillis().toString(),
            )
            viewModelScope.launch {
                repository.addRecipe(newRecipe)
            }
            showShortToast(context = context, context.getString(R.string.added_successfully))
            navController.navigate(Screen.ProfileScreen.route) {
                this.launchSingleTop = true
                popUpTo(Screen.HomeScreen.route) {
                    inclusive = false
                }
            }
        } else {
            showShortToast(context = context,
                context.getString(R.string.please_fill_all_non_optional_fields))
        }
    }
}
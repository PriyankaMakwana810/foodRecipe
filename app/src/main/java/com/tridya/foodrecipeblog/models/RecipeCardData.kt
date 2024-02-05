package com.tridya.foodrecipeblog.models

import com.tridya.foodrecipeblog.database.tables.RecipeCard

/*@Entity(tableName = "recipe_table")
data class RecipeCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idMeal: String = "",
    val strMeal: String,
    val strMealThumb: String,
    val timeToCook: Long = 10L,
    val tagline: String = "tagline",
    val isSaved: Boolean = false,
    val isSearched: Boolean = false,
    val area: String = "",
    val category: String = "",
    val ratings: Float = 4.0f,
    var postedBy: String = "James Milner",
    val userProfilePhoto: String = "https://images.unsplash.com/photo-1705582033498-e7384d494759?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
)*/

val recipesByCountry = listOf(
    RecipeCard(
        id = 0,
        strMeal = "Classic Greek Salad",
        strMealThumb = "https://source.unsplash.com/pGM4sjt_BdQ",
        timeToCook = 10L,
        tagline = "A tag line",
        isSaved = true,
        postedBy = "Laura wilson"
    ),
    RecipeCard(
        id = 1,
        strMeal = "Steak with tomato sauce and bulgur rice.",
        strMealThumb = "https://source.unsplash.com/Yc5sL-ejk6U",
        timeToCook = 10L,
        tagline = "A tag line",
    ),
    RecipeCard(
        id = 2,
        strMeal = "Pilaf sweet with lamb-and-raisins",
        strMealThumb = "https://source.unsplash.com/-LojFX9NfPY",
        timeToCook = 10L,
        tagline = "A tag line",
        postedBy = "Laura wilson"
    ),
    RecipeCard(
        id = 3,
        strMeal = "Froyo",
        strMealThumb = "https://source.unsplash.com/3U2V5WqK1PQ",
        timeToCook = 10L,
        tagline = "A tag line",
    ),
    RecipeCard(
        id = 4,
        strMeal = "Gingerbread",
        strMealThumb = "https://source.unsplash.com/Y4YR9OjdIMk",
        timeToCook = 10L,
        tagline = "A tag line",
        isSaved = true,
        postedBy = "Laura wilson"
    ),
    RecipeCard(
        id = 5,
        strMeal = "Honeycomb",
        strMealThumb = "https://source.unsplash.com/bELvIg_KZGU",
        timeToCook = 10L,
        tagline = "A tag line",
    ),
    RecipeCard(
        id = 6,
        strMeal = "Ice Cream Sandwich",
        strMealThumb = "https://source.unsplash.com/YgYJsFDd4AU",
        timeToCook = 10L,
        tagline = "A tag line",
        postedBy = "Laura wilson"
    ),
    RecipeCard(
        id = 7,
        strMeal = "Jellybean",
        strMealThumb = "https://source.unsplash.com/0u_vbeOkMpk",
        timeToCook = 10L,
        tagline = "A tag line",
    ),
    RecipeCard(
        id = 8,
        strMeal = "KitKat",
        strMealThumb = "https://source.unsplash.com/yb16pT5F_jE",
        timeToCook = 10L,
        tagline = "A tag line",
        isSaved = true
    ),
    RecipeCard(
        id = 9,
        strMeal = "Lollipop",
        strMealThumb = "https://source.unsplash.com/AHF_ZktTL6Q",
        timeToCook = 10L,
        tagline = "A tag line",
        postedBy = "Laura wilson"
    )
)
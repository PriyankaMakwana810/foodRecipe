package com.tridya.foodrecipeblog.models

data class RecipeCard(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val timeToCook: Long,
    val tagline: String = "",
    val tags: Set<String> = emptySet(),
    val isSaved: Boolean = false,
    val ratings: Float = 4.0f,
    val postedBy: String = "James Milner"
)

val recipesByCountry = listOf(
    RecipeCard(
        id = 1,
        name = "Classic Greek Salad",
        imageUrl = "https://source.unsplash.com/pGM4sjt_BdQ",
        timeToCook = 10L,
        tagline = "A tag line",
        isSaved = true,
        postedBy = "Laura wilson"
    ),
    RecipeCard(
        id = 2,
        name = "Steak with tomato sauce and bulgur rice.",
        imageUrl = "https://source.unsplash.com/Yc5sL-ejk6U",
        timeToCook = 10L,
        tagline = "A tag line",
    ),
    RecipeCard(
        id = 3,
        name = "Pilaf sweet with lamb-and-raisins",
        imageUrl = "https://source.unsplash.com/-LojFX9NfPY",
        timeToCook = 10L,
        tagline = "A tag line",
        postedBy = "Laura wilson"
    ),
    RecipeCard(
        id = 4,
        name = "Froyo",
        imageUrl = "https://source.unsplash.com/3U2V5WqK1PQ",
        timeToCook = 10L,
        tagline = "A tag line",
    ),
    RecipeCard(
        id = 5,
        name = "Gingerbread",
        imageUrl = "https://source.unsplash.com/Y4YR9OjdIMk",
        timeToCook = 10L,
        tagline = "A tag line",
        isSaved = true,
        postedBy = "Laura wilson"
    ),
    RecipeCard(
        id = 6,
        name = "Honeycomb",
        imageUrl = "https://source.unsplash.com/bELvIg_KZGU",
        timeToCook = 10L,
        tagline = "A tag line",
    ),
    RecipeCard(
        id = 7,
        name = "Ice Cream Sandwich",
        imageUrl = "https://source.unsplash.com/YgYJsFDd4AU",
        timeToCook = 10L,
        tagline = "A tag line",
        postedBy = "Laura wilson"
    ),
    RecipeCard(
        id = 8,
        name = "Jellybean",
        imageUrl = "https://source.unsplash.com/0u_vbeOkMpk",
        timeToCook = 10L,
        tagline = "A tag line",
    ),
    RecipeCard(
        id = 9,
        name = "KitKat",
        imageUrl = "https://source.unsplash.com/yb16pT5F_jE",
        timeToCook = 10L,
        tagline = "A tag line",
        isSaved = true
    ),
    RecipeCard(
        id = 10,
        name = "Lollipop",
        imageUrl = "https://source.unsplash.com/AHF_ZktTL6Q",
        timeToCook = 10L,
        tagline = "A tag line",
        postedBy = "Laura wilson"
    )
)
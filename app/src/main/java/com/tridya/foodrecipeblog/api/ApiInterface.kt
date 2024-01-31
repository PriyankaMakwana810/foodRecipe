package com.tridya.foodrecipeblog.api

import com.tridya.foodrecipeblog.api.response.CuisinesResponse
import com.tridya.foodrecipeblog.api.response.GetAreaResponse
import com.tridya.foodrecipeblog.api.response.GetCategoriesResponse
import com.tridya.foodrecipeblog.api.response.GetIngredientsResponse
import com.tridya.foodrecipeblog.api.response.LoginResponse
import com.tridya.foodrecipeblog.api.response.RecipeDetailsResponse
import com.tridya.foodrecipeblog.api.response.RecipesByAreaResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET(ApiConstants.END_POINTS_LOGIN)
    fun login(): Single<LoginResponse>

    @GET(ApiConstants.END_POINTS_REGISTER)
    fun register(): Single<LoginResponse>

    @GET("/cuisines")
    suspend fun getCountries(): CuisinesResponse

    @GET("api/json/v1/1/list.php")
    suspend fun getAreaList(
        @Query("a") area: String = "list",
    ): GetAreaResponse

    @GET("api/json/v1/1/list.php")
    suspend fun getCategoriesList(
        @Query("c") category: String = "list",
    ): GetCategoriesResponse

    @GET("api/json/v1/1/list.php")
    suspend fun getIngredientsList(
        @Query("i") ingredient: String = "list",
    ): GetIngredientsResponse

    @GET("api/json/v1/1/filter.php")
    suspend fun getRecipesByArea(
        @Query("a") area: String,
    ): RecipesByAreaResponse

    @GET("api/json/v1/1/filter.php")
    suspend fun getRecipesByCategories(
        @Query("c") category: String,
    ): RecipesByAreaResponse

    @GET("api/json/v1/1/filter.php")
    suspend fun getRecipesByIngredients(
        @Query("i") ingredient: String,
    ): RecipesByAreaResponse

    @GET("api/json/v1/1/search.php")
    suspend fun searchRecipesByName(
        @Query("s") name: String,
    ): RecipeDetailsResponse

    @GET("api/json/v1/1/search.php")
    suspend fun searchRecipesByFirstLetter(
        @Query("f") firstLetter: String,
    ): RecipeDetailsResponse

    @GET("api/json/v1/1/lookup.php")
    suspend fun getRecipeDetailsByID(
        @Query("i") recipeId: String,
    ):RecipeDetailsResponse
}
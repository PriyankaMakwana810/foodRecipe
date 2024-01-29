package com.tridya.foodrecipeblog.api

import com.tridya.foodrecipeblog.api.response.CuisinesResponse
import com.tridya.foodrecipeblog.api.response.GetAreaResponse
import com.tridya.foodrecipeblog.api.response.LoginResponse
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
}
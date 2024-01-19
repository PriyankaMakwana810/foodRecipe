package com.tridya.foodrecipeblog.api

import com.tridya.foodrecipeblog.api.response.CuisinesResponse
import com.tridya.foodrecipeblog.api.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET(ApiConstants.END_POINTS_LOGIN)
    fun login(): Single<LoginResponse>

    @GET(ApiConstants.END_POINTS_REGISTER)
    fun register(): Single<LoginResponse>

    @GET("/cuisines")
    fun getCountries(): CuisinesResponse
}
package com.tridya.foodrecipeblog.api

import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET(ApiConstants.END_POINTS_LOGIN)
    fun login(): Single<LoginResponse>

    @GET(ApiConstants.END_POINTS_REGISTER)
    fun register(): Single<LoginResponse>
}
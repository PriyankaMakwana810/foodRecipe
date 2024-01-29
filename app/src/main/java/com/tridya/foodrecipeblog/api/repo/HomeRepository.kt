package com.tridya.foodrecipeblog.api.repo

import com.tridya.foodrecipeblog.api.ApiConstants
import com.tridya.foodrecipeblog.api.ApiInterface
import javax.inject.Inject
import javax.inject.Named

class HomeRepository @Inject constructor(@Named(ApiConstants.RECIPE_API_SERVICE) private val apiInterface: ApiInterface) {
    fun getCountries() = apiInterface.getCountries()
}
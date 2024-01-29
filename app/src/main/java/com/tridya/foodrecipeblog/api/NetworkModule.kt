package com.tridya.foodrecipeblog.api

import com.tridya.foodrecipeblog.api.ApiConstants.MEALDB_API_SERVICE
import com.tridya.foodrecipeblog.api.ApiConstants.MEALDB_BASE
import com.tridya.foodrecipeblog.api.ApiConstants.MEALDB_RETROFIT
import com.tridya.foodrecipeblog.api.ApiConstants.RECIPE_BASE
import com.tridya.foodrecipeblog.api.ApiConstants.RECIPE_RETROFIT
import com.tridya.foodrecipeblog.api.repo.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String {
        return ApiConstants.BASE_URL
    }

    @Singleton
    @Provides
    @Named(MEALDB_BASE)
    fun ProvideMeadDbBaseURL(): String{
        return ApiConstants.MEALDB_BASE_URL
    }
    @Singleton
    @Provides
    @Named(RECIPE_BASE)
    fun ProvideRecipeBaseUrl(): String {
        return ApiConstants.RECIPE_URL
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            /*.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("X-RapidAPI-Key", "147683b500msha3dafe202f0bc70p187692jsn29814410c05e")
                    .header("X-RapidAPI-Host", "all-in-one-recipe-api.p.rapidapi.com")
                    .build()
                chain.proceed(request)
            }*/
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return retrofit.build()
    }


    @Singleton
    @Provides
    @Named(MEALDB_RETROFIT)
    fun provideMealDbRetrofit(
        @Named(MEALDB_BASE) baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return retrofit.build()
    }
    @Singleton
    @Provides
    @Named(RECIPE_RETROFIT)
    fun provideRecipeRetrofit(
        @Named(RECIPE_BASE) baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return retrofit.build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


    @Singleton
    @Provides
    @Named(ApiConstants.RECIPE_API_SERVICE)
    fun provideRecipeApiService(@Named(RECIPE_RETROFIT) retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
    @Singleton
    @Provides
    @Named(MEALDB_API_SERVICE)
    fun provideMealDbApiService(@Named(MEALDB_RETROFIT) retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}
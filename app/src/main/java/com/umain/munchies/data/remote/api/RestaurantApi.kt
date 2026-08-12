package com.umain.munchies.data.remote.api

import com.umain.munchies.data.remote.dto.FilterDto
import com.umain.munchies.data.remote.dto.OpenStatusDto
import com.umain.munchies.data.remote.dto.RestaurantsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {

    @GET("restaurants")
    suspend fun getRestaurants(): RestaurantsResponseDto

    @GET("filter/{id}")
    suspend fun getFilter(@Path("id") id: String): FilterDto

    @GET("open/{id}")
    suspend fun getOpenStatus(@Path("id") id: String): OpenStatusDto
}
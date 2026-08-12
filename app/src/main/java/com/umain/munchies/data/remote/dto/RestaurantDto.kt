package com.umain.munchies.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantsResponseDto(
    val restaurants: List<RestaurantDto>
)

@Serializable
data class RestaurantDto(
    val id: String,
    val name: String,
    val rating: Double,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("delivery_time_minutes")
    val deliveryTimeMinutes: Int,
    val filterIds: List<String>
)
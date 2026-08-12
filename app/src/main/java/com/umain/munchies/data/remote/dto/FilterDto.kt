package com.umain.munchies.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterDto(
    val id: String,
    val name: String,
    @SerialName("image_url")
    val imageUrl: String
)
package com.umain.munchies.domain.model

data class Restaurant(
    val id: String,
    val name: String,
    val rating: Double,
    val imageUrl: String,
    val deliveryTimeMinutes: Int,
    val filterIds: List<String>,
    val isOpen: Boolean? = null
)
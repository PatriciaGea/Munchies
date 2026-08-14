package com.umain.munchies.ui.restaurantdetail

import com.umain.munchies.domain.model.Restaurant

data class RestaurantDetailUiState(
    val isLoading: Boolean = false,
    val restaurant: Restaurant? = null,
    val isOpen: Boolean? = null,
    val errorMessage: String? = null
)
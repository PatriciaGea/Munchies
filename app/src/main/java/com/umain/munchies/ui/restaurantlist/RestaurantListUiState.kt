package com.umain.munchies.ui.restaurantlist

import com.umain.munchies.domain.model.Filter
import com.umain.munchies.domain.model.Restaurant

data class RestaurantListUiState(
    val isLoading: Boolean = false,
    val restaurants: List<Restaurant> = emptyList(),
    val filters: List<Filter> = emptyList(),
    val selectedFilterIds: Set<String> = emptySet(),
    val errorMessage: String? = null
)
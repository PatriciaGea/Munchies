package com.umain.munchies.domain.repository

import com.umain.munchies.domain.model.Filter
import com.umain.munchies.domain.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurants(): List<Restaurant>
    suspend fun getFilters(filterIds: List<String>): List<Filter>
    suspend fun getOpenStatus(restaurantId: String): Boolean
}
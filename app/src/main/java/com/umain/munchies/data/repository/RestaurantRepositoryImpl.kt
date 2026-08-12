package com.umain.munchies.data.repository

import com.umain.munchies.data.mapper.toDomain
import com.umain.munchies.data.remote.api.RestaurantApi
import com.umain.munchies.domain.model.Filter
import com.umain.munchies.domain.model.Restaurant
import com.umain.munchies.domain.repository.RestaurantRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi
) : RestaurantRepository {

    override suspend fun getRestaurants(): List<Restaurant> {
        return api.getRestaurants().restaurants.map { it.toDomain() }
    }

    override suspend fun getFilters(filterIds: List<String>): List<Filter> = coroutineScope {
        filterIds.map { id ->
            async { api.getFilter(id).toDomain() }
        }.map { it.await() }
    }

    override suspend fun getOpenStatus(restaurantId: String): Boolean {
        return api.getOpenStatus(restaurantId).isCurrentlyOpen
    }
}
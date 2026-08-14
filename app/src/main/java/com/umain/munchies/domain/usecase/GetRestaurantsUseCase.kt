package com.umain.munchies.domain.usecase

import com.umain.munchies.domain.model.Restaurant
import com.umain.munchies.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getRestaurants()
    }
}
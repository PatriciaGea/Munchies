package com.umain.munchies.domain.usecase

import com.umain.munchies.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetOpenStatusUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(restaurantId: String): Boolean {
        return repository.getOpenStatus(restaurantId)
    }
}
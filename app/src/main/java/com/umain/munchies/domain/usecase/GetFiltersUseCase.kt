package com.umain.munchies.domain.usecase

import com.umain.munchies.domain.model.Filter
import com.umain.munchies.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(filterIds: List<String>): List<Filter> {
        return repository.getFilters(filterIds)
    }
}
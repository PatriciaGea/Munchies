package com.umain.munchies.ui.restaurantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umain.munchies.domain.usecase.GetFiltersUseCase
import com.umain.munchies.domain.usecase.GetRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getFiltersUseCase: GetFiltersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RestaurantListUiState())
    val uiState: StateFlow<RestaurantListUiState> = _uiState.asStateFlow()

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val restaurants = getRestaurantsUseCase()
                val allFilterIds = restaurants.flatMap { it.filterIds }.distinct()
                val filters = getFiltersUseCase(allFilterIds)

                _uiState.update {
                    it.copy(isLoading = false, restaurants = restaurants, filters = filters)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = "Não foi possível carregar os restaurantes")
                }
            }
        }
    }

    fun toggleFilter(filterId: String) {
        _uiState.update { current ->
            val newSelection = if (filterId in current.selectedFilterIds) {
                current.selectedFilterIds - filterId
            } else {
                current.selectedFilterIds + filterId
            }
            current.copy(selectedFilterIds = newSelection)
        }
    }
}
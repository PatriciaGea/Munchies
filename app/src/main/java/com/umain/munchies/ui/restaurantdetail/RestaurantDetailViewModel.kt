package com.umain.munchies.ui.restaurantdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umain.munchies.domain.usecase.GetOpenStatusUseCase
import com.umain.munchies.domain.usecase.GetRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getOpenStatusUseCase: GetOpenStatusUseCase
) : ViewModel() {

    private val restaurantId: String = checkNotNull(savedStateHandle["restaurantId"])

    private val _uiState = MutableStateFlow(RestaurantDetailUiState())
    val uiState: StateFlow<RestaurantDetailUiState> = _uiState.asStateFlow()

    init {
        loadRestaurant()
    }

    private fun loadRestaurant() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val restaurant = getRestaurantsUseCase().find { it.id == restaurantId }
                val isOpen = getOpenStatusUseCase(restaurantId)

                _uiState.update {
                    it.copy(isLoading = false, restaurant = restaurant, isOpen = isOpen)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = "Não foi possível carregar o restaurante")
                }
            }
        }
    }
}
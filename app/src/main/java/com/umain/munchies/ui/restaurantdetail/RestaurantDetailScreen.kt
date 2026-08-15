package com.umain.munchies.ui.restaurantdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umain.munchies.ui.restaurantdetail.components.RestaurantBanner
import com.umain.munchies.ui.restaurantdetail.components.RestaurantInfoCard

@Composable
fun RestaurantDetailScreen(
    onBackClick: () -> Unit,
    viewModel: RestaurantDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            uiState.errorMessage != null -> {
                Text(
                    text = uiState.errorMessage.orEmpty(),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            uiState.restaurant != null -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box {
                        RestaurantBanner(
                            imageUrl = uiState.restaurant!!.imageUrl,
                            onBackClick = onBackClick
                        )
                        RestaurantInfoCard(
                            name = uiState.restaurant!!.name,
                            subtitle = uiState.restaurant!!.filterIds
                                .mapNotNull { id -> uiState.filters.find { it.id == id }?.name }
                                .joinToString(" • "),
                            isOpen = uiState.isOpen,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .offset(y = 175.dp)
                        )
                    }
                }
            }
        }
    }
}
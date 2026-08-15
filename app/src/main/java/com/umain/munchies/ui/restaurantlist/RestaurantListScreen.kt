package com.umain.munchies.ui.restaurantlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umain.munchies.ui.restaurantlist.components.FilterChip
import com.umain.munchies.ui.restaurantlist.components.RestaurantCard
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import com.umain.munchies.ui.restaurantlist.components.HeaderSection

@Composable
fun RestaurantListScreen(
    onRestaurantClick: (String) -> Unit,
    viewModel: RestaurantListViewModel = hiltViewModel()
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
            else -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    val filteredRestaurants = if (uiState.selectedFilterIds.isEmpty()) {
                        uiState.restaurants
                    } else {
                        uiState.restaurants.filter { restaurant ->
                            uiState.selectedFilterIds.all { it in restaurant.filterIds }
                        }
                    }
                    HeaderSection()

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(9.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.background,
                                        MaterialTheme.colorScheme.background.copy(alpha = 0f)
                                    )
                                )
                            )
                    )

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 12.dp)
                    ) {
                        items(uiState.filters) { filter ->
                            FilterChip(
                                filter = filter,
                                isSelected = filter.id in uiState.selectedFilterIds,
                                onClick = { viewModel.toggleFilter(filter.id) }
                            )
                        }
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(filteredRestaurants) { restaurant ->
                            val subtitle = restaurant.filterIds
                                .mapNotNull { id -> uiState.filters.find { it.id == id }?.name }
                                .joinToString(" • ")

                            RestaurantCard(
                                restaurant = restaurant,
                                subtitle = subtitle,
                                onClick = { onRestaurantClick(restaurant.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
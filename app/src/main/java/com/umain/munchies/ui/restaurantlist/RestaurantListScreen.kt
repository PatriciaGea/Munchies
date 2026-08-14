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
                    val filteredRestaurants = if (uiState.selectedFilterIds.isEmpty()) {
                        uiState.restaurants
                    } else {
                        uiState.restaurants.filter { restaurant ->
                            uiState.selectedFilterIds.all { it in restaurant.filterIds }
                        }
                    }
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(filteredRestaurants) { restaurant ->
                            RestaurantCard(
                                restaurant = restaurant,
                                onClick = { onRestaurantClick(restaurant.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
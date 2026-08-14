package com.umain.munchies.ui.restaurantdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.umain.munchies.ui.theme.MunchiesClosed
import com.umain.munchies.ui.theme.MunchiesOpen

@Composable
fun RestaurantInfoCard(
    name: String,
    isOpen: Boolean?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        when (isOpen) {
            true -> Text(
                text = "Open",
                style = MaterialTheme.typography.titleMedium,
                color = MunchiesOpen
            )
            false -> Text(
                text = "Closed",
                style = MaterialTheme.typography.titleMedium,
                color = MunchiesClosed
            )
            null -> Text(
                text = "...",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
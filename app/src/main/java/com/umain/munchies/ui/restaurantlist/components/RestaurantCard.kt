package com.umain.munchies.ui.restaurantlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.umain.munchies.domain.model.Restaurant
import com.umain.munchies.ui.theme.MunchiesCardBackground
import com.umain.munchies.ui.theme.MunchiesRatingText
import com.umain.munchies.ui.theme.MunchiesSubtitle
import com.umain.munchies.ui.theme.MunchiesText

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(343.dp)
            .height(196.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(MunchiesCardBackground)
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = restaurant.imageUrl,
            contentDescription = restaurant.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(132.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = restaurant.name, fontSize = 18.sp, color = MunchiesText)
                Text(text = subtitle, fontSize = 12.sp, color = MunchiesSubtitle)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Schedule,
                        contentDescription = null,
                        tint = Color(0xFFFF5252),
                        modifier = Modifier.size(10.dp)
                    )
                    Text(text = "${restaurant.deliveryTimeMinutes} min", fontSize = 10.sp, color = MunchiesRatingText)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(12.dp)
                )
                Text(text = "${restaurant.rating}", fontSize = 10.sp, color = MunchiesRatingText)
            }
        }
    }
}
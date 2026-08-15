package com.umain.munchies.ui.restaurantdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umain.munchies.ui.theme.MunchiesCardBackground
import com.umain.munchies.ui.theme.MunchiesClosed
import com.umain.munchies.ui.theme.MunchiesOpen
import com.umain.munchies.ui.theme.MunchiesSubtitle
import com.umain.munchies.ui.theme.MunchiesText

@Composable
fun RestaurantInfoCard(
    name: String,
    subtitle: String,
    isOpen: Boolean?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(MunchiesCardBackground)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(text = name, fontSize = 24.sp, color = MunchiesText)
        if (subtitle.isNotEmpty()) {
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = MunchiesSubtitle,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        when (isOpen) {
            true -> Text(text = "Open", fontSize = 18.sp, color = MunchiesOpen)
            false -> Text(text = "Closed", fontSize = 18.sp, color = MunchiesClosed)
            null -> Text(text = "...", fontSize = 18.sp, color = MunchiesText)
        }
    }
}
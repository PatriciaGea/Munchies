package com.umain.munchies.ui.restaurantlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.umain.munchies.R

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(98.dp)
            .padding(start = 16.dp, top = 44.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_umain_logo),
            contentDescription = "Umain logo",
            modifier = Modifier.size(width = 54.5.dp, height = 54.dp)
        )
    }
}
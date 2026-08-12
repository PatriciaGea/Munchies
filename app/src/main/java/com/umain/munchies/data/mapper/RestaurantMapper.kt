package com.umain.munchies.data.mapper

import com.umain.munchies.data.remote.dto.FilterDto
import com.umain.munchies.data.remote.dto.RestaurantDto
import com.umain.munchies.domain.model.Filter
import com.umain.munchies.domain.model.Restaurant

fun RestaurantDto.toDomain(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        rating = rating,
        imageUrl = imageUrl,
        deliveryTimeMinutes = deliveryTimeMinutes,
        filterIds = filterIds
    )
}

fun FilterDto.toDomain(): Filter {
    return Filter(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}
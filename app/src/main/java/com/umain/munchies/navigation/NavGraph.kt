package com.umain.munchies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.umain.munchies.ui.restaurantdetail.RestaurantDetailScreen
import com.umain.munchies.ui.restaurantlist.RestaurantListScreen

object MunchiesDestinations {
    const val RESTAURANT_LIST = "restaurant_list"
    const val RESTAURANT_DETAIL = "restaurant_detail/{restaurantId}"

    fun restaurantDetailRoute(restaurantId: String) = "restaurant_detail/$restaurantId"
}

@Composable
fun MunchiesNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MunchiesDestinations.RESTAURANT_LIST
    ) {
        composable(MunchiesDestinations.RESTAURANT_LIST) {
            RestaurantListScreen(
                onRestaurantClick = { restaurantId ->
                    navController.navigate(
                        MunchiesDestinations.restaurantDetailRoute(restaurantId)
                    )
                }
            )
        }
        composable(MunchiesDestinations.RESTAURANT_DETAIL) {
            RestaurantDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
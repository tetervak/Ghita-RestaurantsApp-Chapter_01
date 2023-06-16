package com.codingtroops.restaurantsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codingtroops.restaurantsapp.ui.navigation.RestaurantNavHost

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    RestaurantNavHost(navController)
}


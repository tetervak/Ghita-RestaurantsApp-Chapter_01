package ca.tetervak.restaurantapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.tetervak.restaurantapp.ui.navigation.RestaurantNavHost

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    RestaurantNavHost(navController)
}


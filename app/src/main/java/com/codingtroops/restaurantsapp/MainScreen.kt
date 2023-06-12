package com.codingtroops.restaurantsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.codingtroops.restaurantsapp.model.Restaurant
import com.codingtroops.restaurantsapp.screens.details.DetailsScreen
import com.codingtroops.restaurantsapp.screens.details.DetailsViewModel
import com.codingtroops.restaurantsapp.screens.list.ListScreen
import com.codingtroops.restaurantsapp.screens.list.ListViewModel
import com.codingtroops.restaurantsapp.ui.theme.MainTheme

@Composable
fun MainScreen() {
    MainTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "restaurants") {
            composable(route = "restaurants") {
                val viewModel: ListViewModel = hiltViewModel()
                val restaurants: List<Restaurant> =
                    viewModel.restaurantListFlow.collectAsState(initial = emptyList()).value
                ListScreen(
                    restaurants = restaurants,
                    onItemClick = { id -> navController.navigate("restaurants/$id") },
                    onFavoriteClick = { id -> viewModel.toggleFavorite(id) }
                )
            }
            composable(
                route = "restaurants/{restaurant_id}",
                arguments = listOf(navArgument("restaurant_id") { type = NavType.IntType }),
                deepLinks = listOf(navDeepLink { uriPattern =
                    "www.restaurantsapp.details.com/{restaurant_id}" }),
            ) {
                val viewModel: DetailsViewModel = hiltViewModel()
                DetailsScreen(restaurant = viewModel.restaurantState.value)
            }
        }
    }
}
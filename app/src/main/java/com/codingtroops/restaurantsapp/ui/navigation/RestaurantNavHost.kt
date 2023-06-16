package com.codingtroops.restaurantsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.codingtroops.restaurantsapp.ui.details.DetailsScreen
import com.codingtroops.restaurantsapp.ui.details.DetailsViewModel
import com.codingtroops.restaurantsapp.ui.list.ListScreen
import com.codingtroops.restaurantsapp.ui.list.ListViewModel

@Composable
fun RestaurantNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = ListDestination.route) {
        composable(route = ListDestination.route) {
            val viewModel: ListViewModel = hiltViewModel()
            ListScreen(
                viewModel = viewModel,
                onItemClick = { id ->
                    navController.navigate("${DetailsDestination.route}/$id")
                },
            )
        }
        composable(
            route = DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.restaurantIdArg) {
                type = NavType.IntType
            })
        ) {
            val viewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(viewModel = viewModel, navigateBack = { navController.popBackStack() })
        }
    }
}
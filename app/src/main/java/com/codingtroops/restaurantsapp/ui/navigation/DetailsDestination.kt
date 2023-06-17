package com.codingtroops.restaurantsapp.ui.navigation

import com.codingtroops.restaurantsapp.R

object DetailsDestination : NavDestination {
    override val route = "restaurant_details"
    override val titleRes = R.string.nav_details_title
    const val restaurantIdArg = "restaurant_id"
    val routeWithArgs = "$route/{$restaurantIdArg}"
}
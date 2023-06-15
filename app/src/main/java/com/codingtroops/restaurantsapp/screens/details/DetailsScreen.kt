package com.codingtroops.restaurantsapp.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codingtroops.restaurantsapp.R
import com.codingtroops.restaurantsapp.model.Restaurant
import com.codingtroops.restaurantsapp.screens.common.RestaurantDetails
import com.codingtroops.restaurantsapp.screens.common.RestaurantIcon

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel
) {
    val detailsUiState: DetailsUiState = viewModel.detailsUiState.collectAsState().value
    if (detailsUiState is DetailsUiState.Success) {
        val restaurant: Restaurant = detailsUiState.restaurant
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            RestaurantIcon(
                icon = Icons.Filled.Place,
                modifier = Modifier.padding(top = 32.dp, bottom = 32.dp)
            )
            RestaurantDetails(
                title = restaurant.title,
                description = restaurant.description,
                modifier = Modifier.padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            Text(stringResource(R.string.more_info_coming_soon))
        }
    }
}
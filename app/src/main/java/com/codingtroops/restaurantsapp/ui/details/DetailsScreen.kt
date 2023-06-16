package com.codingtroops.restaurantsapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codingtroops.restaurantsapp.R
import com.codingtroops.restaurantsapp.domain.Restaurant
import com.codingtroops.restaurantsapp.ui.common.RestaurantDetails
import com.codingtroops.restaurantsapp.ui.common.RestaurantIcon
import com.codingtroops.restaurantsapp.ui.common.RestaurantTopAppBar
import com.codingtroops.restaurantsapp.ui.navigation.DetailsDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    navigateBack: () -> Unit,
) {
    val state: State<DetailsUiState> = viewModel.detailsUiState.collectAsState()
    val detailsUiState: DetailsUiState = state.value

    Scaffold(
        topBar = {
            RestaurantTopAppBar(
                title = stringResource(DetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        if (detailsUiState is DetailsUiState.Success) {
            DetailsBody(
                restaurant = detailsUiState.restaurant,
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}

@Composable
private fun DetailsBody(
    restaurant: Restaurant,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
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
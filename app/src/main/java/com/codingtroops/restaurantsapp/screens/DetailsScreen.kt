package com.codingtroops.restaurantsapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codingtroops.restaurantsapp.DetailsViewModel
import com.codingtroops.restaurantsapp.R
import com.codingtroops.restaurantsapp.screens.common.RestaurantDetails
import com.codingtroops.restaurantsapp.screens.common.RestaurantIcon

@Composable
fun DetailsScreen() {
    val viewModel: DetailsViewModel = viewModel()
    val item = viewModel.uiState.value
    if (item != null) {
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
                title = item.title,
                description = item.description,
                modifier = Modifier.padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            Text(stringResource(R.string.more_info_coming_soon))
        }
    }
}
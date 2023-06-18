package com.codingtroops.restaurantsapp.ui.details

import com.codingtroops.restaurantsapp.domain.Restaurant

sealed interface DetailsUiState {
    object Loading : DetailsUiState
    data class Success(val restaurant: Restaurant) : DetailsUiState
}
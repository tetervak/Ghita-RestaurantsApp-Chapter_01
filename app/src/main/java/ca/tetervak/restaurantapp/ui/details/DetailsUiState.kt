package ca.tetervak.restaurantapp.ui.details

import ca.tetervak.restaurantapp.domain.Restaurant

sealed interface DetailsUiState {
    object Loading : DetailsUiState
    data class Success(val restaurant: Restaurant) : DetailsUiState
}
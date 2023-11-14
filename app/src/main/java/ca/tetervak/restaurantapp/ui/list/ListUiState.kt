package ca.tetervak.restaurantapp.ui.list

import ca.tetervak.restaurantapp.domain.Restaurant

sealed interface ListUiState {
    object Loading : ListUiState
    data class Success(val restaurants: List<Restaurant>) : ListUiState
}
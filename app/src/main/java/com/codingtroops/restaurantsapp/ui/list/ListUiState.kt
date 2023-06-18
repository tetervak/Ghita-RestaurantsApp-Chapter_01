package com.codingtroops.restaurantsapp.ui.list

import com.codingtroops.restaurantsapp.domain.Restaurant

sealed interface ListUiState {
    object Loading : ListUiState
    data class Success(val restaurants: List<Restaurant>) : ListUiState
}
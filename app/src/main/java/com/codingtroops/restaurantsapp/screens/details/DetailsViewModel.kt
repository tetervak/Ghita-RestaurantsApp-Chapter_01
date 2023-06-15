package com.codingtroops.restaurantsapp.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    repository: RestaurantRepository,
    stateHandle: SavedStateHandle
): ViewModel() {

    private val _detailsUiState: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState.Loading)
    val detailsUiState: StateFlow<DetailsUiState> = _detailsUiState

    init {
        val id: Int = stateHandle.get<Int>("restaurant_id")!!
        viewModelScope.launch {
            val restaurant: Restaurant = repository.getRestaurantById(id)
            _detailsUiState.update {
                DetailsUiState.Success(restaurant = restaurant)
            }
        }
    }
}

sealed interface DetailsUiState {
    object Loading: DetailsUiState
    data class Success(val restaurant: Restaurant): DetailsUiState
}
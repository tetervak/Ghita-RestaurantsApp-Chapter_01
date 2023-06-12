package com.codingtroops.restaurantsapp.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    repository: RestaurantRepository,
    stateHandle: SavedStateHandle
): ViewModel() {

    private val _restaurantState: MutableState<Restaurant?> = mutableStateOf(null)
    val restaurantState: State<Restaurant?> = _restaurantState

    init {
        val id: Int = stateHandle.get<Int>("restaurant_id")!!
        viewModelScope.launch {
            _restaurantState.value = repository.getRestaurantById(id)
        }
    }
}
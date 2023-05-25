package com.codingtroops.restaurantsapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    private val _uiState: MutableState<List<Restaurant>> =
        mutableStateOf(dummyRestaurants)
    val uiState: State<List<Restaurant>> = _uiState

    fun toggleFavorite(id: Int) {
        val restaurants = uiState.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        _uiState.value = restaurants.toList()
    }
}
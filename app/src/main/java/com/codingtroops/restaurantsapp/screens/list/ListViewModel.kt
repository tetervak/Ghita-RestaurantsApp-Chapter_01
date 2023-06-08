package com.codingtroops.restaurantsapp.screens.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.data.RestaurantRepository
import com.codingtroops.restaurantsapp.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    repository: RestaurantRepository
) : ViewModel() {

    private val _uiState: MutableState<List<Restaurant>> =
        mutableStateOf(emptyList())
    val uiState: State<List<Restaurant>> = _uiState

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        viewModelScope.launch(errorHandler) {
            _uiState.value = repository.getAllRestaurants()
        }
    }

    fun toggleFavorite(id: Int) {
        val restaurants = uiState.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        _uiState.value = restaurants.toList()
    }
}
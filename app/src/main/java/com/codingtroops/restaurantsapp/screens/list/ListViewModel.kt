package com.codingtroops.restaurantsapp.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    val restaurantListFlow: Flow<List<Restaurant>> = repository.getAllRestaurantFlow()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        viewModelScope.launch(errorHandler) {
            repository.refresh()
        }
    }

    fun toggleFavorite(id: Int) =
        viewModelScope.launch(errorHandler) {
            repository.toggleIsFavoriteById(id)
        }
}
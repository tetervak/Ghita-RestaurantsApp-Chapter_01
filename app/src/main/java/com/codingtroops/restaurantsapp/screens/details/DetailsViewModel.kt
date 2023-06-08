package com.codingtroops.restaurantsapp.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.data.RestaurantsApiService
import com.codingtroops.restaurantsapp.model.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsViewModel(stateHandle: SavedStateHandle): ViewModel() {
    private var restInterface: RestaurantsApiService

    private val _uiState: MutableState<Restaurant?> = mutableStateOf(null)
    val uiState: State<Restaurant?> = _uiState

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurants-db-default-rtdb.firebaseio.com/")
            .build()
        restInterface = retrofit.create(RestaurantsApiService::class.java)

        val id: Int = stateHandle.get<Int>("restaurant_id") ?: 0

        viewModelScope.launch {
            val restaurant = getRemoteRestaurant(id)
            _uiState.value = restaurant
        }
    }

    private suspend fun getRemoteRestaurant(id: Int): Restaurant {
        return withContext(Dispatchers.IO) {
            val response =  restInterface.getRestaurant(id)
            return@withContext response.values.first()
        }
    }
}
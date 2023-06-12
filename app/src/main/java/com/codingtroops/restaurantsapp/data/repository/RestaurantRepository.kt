package com.codingtroops.restaurantsapp.data.repository

import com.codingtroops.restaurantsapp.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    suspend fun getAllRestaurants(): List<Restaurant>

    fun getAllRestaurantFlow(): Flow<List<Restaurant>>

    suspend fun getRestaurantById(id: Int): Restaurant

    suspend fun toggleIsFavoriteById(id: Int)

    suspend fun refreshRestaurants()
}
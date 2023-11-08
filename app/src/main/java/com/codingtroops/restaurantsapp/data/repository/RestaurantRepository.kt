package com.codingtroops.restaurantsapp.data.repository

import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    fun getAllRestaurantFlow(): Flow<List<Restaurant>>

    suspend fun getRestaurantById(id: Int): Restaurant?

    suspend fun toggleIsFavoriteById(id: Int)

    suspend fun setIsFavoriteById(id: Int, isFavorite: Boolean)

    suspend fun refreshRestaurants()
}
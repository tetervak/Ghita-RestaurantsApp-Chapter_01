package com.codingtroops.restaurantsapp.data

import com.codingtroops.restaurantsapp.model.Restaurant

interface RestaurantRepository {

    suspend fun getAllRestaurants(): List<Restaurant>

    suspend fun getRestaurantById(id: Int): Restaurant
}
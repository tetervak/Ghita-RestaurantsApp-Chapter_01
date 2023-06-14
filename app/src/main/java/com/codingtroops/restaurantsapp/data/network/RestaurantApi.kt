package com.codingtroops.restaurantsapp.data.network

import com.codingtroops.restaurantsapp.model.Restaurant
import retrofit2.http.GET


interface RestaurantApi {
    @GET("restaurants.json")
    suspend fun getAllRestaurants(): List<Restaurant>
}
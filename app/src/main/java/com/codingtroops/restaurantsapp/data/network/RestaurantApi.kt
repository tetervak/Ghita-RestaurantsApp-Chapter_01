package com.codingtroops.restaurantsapp.data.network

import retrofit2.http.GET


interface RestaurantApi {
    @GET("restaurants.json")
    suspend fun getAllRestaurants(): List<RemoteRestaurant>
}
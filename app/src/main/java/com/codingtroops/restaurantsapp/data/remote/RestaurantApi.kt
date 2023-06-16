package com.codingtroops.restaurantsapp.data.remote

import retrofit2.http.GET


interface RestaurantApi {
    @GET("restaurants.json")
    suspend fun getAllRestaurants(): List<RemoteRestaurant>
}
package com.codingtroops.restaurantsapp.data.network

import com.codingtroops.restaurantsapp.model.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query


interface RestaurantApi {
    @GET("restaurants.json")
    suspend fun getAllRestaurants(): List<Restaurant>

    @GET("restaurants.json?orderBy=\"r_id\"")
    suspend fun getRestaurantById(@Query("equalTo") id: Int): Map<String, Restaurant>
}
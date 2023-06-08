package com.codingtroops.restaurantsapp.data

import com.codingtroops.restaurantsapp.model.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepositoryNet @Inject constructor(
    private val restaurantsApiService: RestaurantsApiService
): RestaurantRepository {

    override suspend fun getAllRestaurants(): List<Restaurant> =
        withContext(Dispatchers.IO){restaurantsApiService.getAllRestaurants()}

    override suspend fun getRestaurantById(id: Int): Restaurant {
        return withContext(Dispatchers.IO) {
            val response =  restaurantsApiService.getRestaurantById(id)
            return@withContext response.values.first()
        }
    }
}
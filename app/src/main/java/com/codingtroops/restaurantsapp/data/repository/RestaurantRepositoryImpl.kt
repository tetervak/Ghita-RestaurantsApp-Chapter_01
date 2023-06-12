package com.codingtroops.restaurantsapp.data.repository

import com.codingtroops.restaurantsapp.data.database.RestaurantDao
import com.codingtroops.restaurantsapp.data.network.RestaurantApi
import com.codingtroops.restaurantsapp.model.IsFavorite
import com.codingtroops.restaurantsapp.model.Restaurant
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantApi: RestaurantApi,
    private val restaurantDao: RestaurantDao
): RestaurantRepository {

    override suspend fun getAllRestaurants(): List<Restaurant> =
        withContext(Dispatchers.IO){
            restaurantApi.getAllRestaurants()
        }

    override fun getAllRestaurantFlow(): Flow<List<Restaurant>> =
        restaurantDao.getAllRestaurantFlow()

    override suspend fun getRestaurantById(id: Int): Restaurant =
        withContext(Dispatchers.IO) {
            //val map =  restaurantApi.getRestaurantById(id)
            //map.values.first()
            restaurantDao.getRestaurantById(id)
        }

    override suspend fun toggleIsFavoriteById(id: Int) =
        withContext(Dispatchers.IO) {
            restaurantDao.toggleIsFavoriteById(id)
        }

    override suspend fun refresh() =
        withContext(Dispatchers.IO) {

            val restaurants: Deferred<List<Restaurant>> = async {
                restaurantApi.getAllRestaurants()
            }

            val idsOfFavorites: List<Int> =
                restaurantDao.getIdsOfFavoriteRestaurants()
            val isFavoriteList: List<IsFavorite> =
                idsOfFavorites.map { id -> IsFavorite(id = id, isFavorite = true) }

            restaurantDao.refreshRestaurants(restaurants.await())
            restaurantDao.updateIsFavorite(isFavoriteList)
        }
}
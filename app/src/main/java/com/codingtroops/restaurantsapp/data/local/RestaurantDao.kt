package com.codingtroops.restaurantsapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getAllRestaurantFlow(): Flow<List<LocalRestaurant>>

    @Query("SELECT * FROM restaurant WHERE r_id=:id")
    suspend fun getRestaurantById(id: Int): LocalRestaurant?

    @Upsert
    suspend fun upsertRestaurants(restaurants: List<LocalRestaurant>)

    @Update(entity = LocalRestaurant::class)
    suspend fun updateIsFavorite(isFavorite: IsFavorite)

    @Update(entity = LocalRestaurant::class)
    suspend fun updateIsFavorite(list: List<IsFavorite>)

    @Query("SELECT r_id FROM restaurant WHERE is_favorite = 1")
    suspend fun getIdsOfFavoriteRestaurants(): List<Int>

    @Query("SELECT is_favorite FROM restaurant WHERE r_id=:id")
    suspend fun getIsFavoriteById(id: Int): Boolean

    @Query("UPDATE restaurant SET is_favorite=:isFavorite WHERE r_id=:id")
    suspend fun setIsFavoriteById(id: Int, isFavorite: Boolean)

    @Transaction
    suspend fun toggleIsFavoriteById(id: Int) {
        val isFavorite: Boolean = getIsFavoriteById(id)
        setIsFavoriteById(id, !isFavorite)
    }

    @Query("DELETE FROM restaurant")
    suspend fun deleteAllRestaurants()

    @Transaction
    suspend fun refreshRestaurants(list: List<LocalRestaurant>) {
        deleteAllRestaurants()
        upsertRestaurants(list)
    }

}
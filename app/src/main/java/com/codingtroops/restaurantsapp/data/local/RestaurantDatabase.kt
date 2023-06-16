package com.codingtroops.restaurantsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalRestaurant::class], version = 2, exportSchema = false
)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract val restaurantDao: RestaurantDao
}
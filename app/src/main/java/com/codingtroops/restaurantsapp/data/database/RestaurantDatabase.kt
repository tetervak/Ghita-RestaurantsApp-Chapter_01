package com.codingtroops.restaurantsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingtroops.restaurantsapp.model.Restaurant

@Database(
    entities = [Restaurant::class],
    version = 2,
    exportSchema = false
)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract val restaurantDao: RestaurantDao
}
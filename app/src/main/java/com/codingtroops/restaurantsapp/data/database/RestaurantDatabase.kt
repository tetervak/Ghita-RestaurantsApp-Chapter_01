package com.codingtroops.restaurantsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingtroops.restaurantsapp.model.Restaurant

@Database(
    entities = [Restaurant::class],
    version = 2,
    exportSchema = false
)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract val restaurantDao: RestaurantDao

    companion object {

        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RestaurantDatabase {
            if(INSTANCE == null){
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): RestaurantDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                RestaurantDatabase::class.java,
                "restaurants_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}
package com.codingtroops.restaurantsapp.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRestaurantDatabase(
        @ApplicationContext applicationContext: Context
    ): RestaurantDatabase = Room.databaseBuilder(
        applicationContext,
        RestaurantDatabase::class.java,
        "restaurants_database")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideRestaurantDao(
        database: RestaurantDatabase
    ): RestaurantDao = database.restaurantDao

}
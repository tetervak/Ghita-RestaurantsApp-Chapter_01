package com.codingtroops.restaurantsapp.data.database

import android.content.Context
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
        @ApplicationContext context: Context
    ): RestaurantDatabase = RestaurantDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideRestaurantDao(
        database: RestaurantDatabase
    ): RestaurantDao = database.restaurantDao

}
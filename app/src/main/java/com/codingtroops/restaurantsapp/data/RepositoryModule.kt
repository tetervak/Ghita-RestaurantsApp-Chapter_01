package com.codingtroops.restaurantsapp.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRestaurantRepository(repository: RestaurantRepositoryNet): RestaurantRepository
}
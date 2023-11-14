package ca.tetervak.restaurantapp.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRestaurantRepository(
        repository: RestaurantRepositoryImpl
    ): RestaurantRepository
}
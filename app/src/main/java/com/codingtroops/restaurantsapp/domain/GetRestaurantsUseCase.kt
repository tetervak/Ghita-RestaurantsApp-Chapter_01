package com.codingtroops.restaurantsapp.domain

import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(): Flow<List<Restaurant>> =
        repository.getAllRestaurantFlow().map { list ->
            list.sortedBy { it.title }
        }
}
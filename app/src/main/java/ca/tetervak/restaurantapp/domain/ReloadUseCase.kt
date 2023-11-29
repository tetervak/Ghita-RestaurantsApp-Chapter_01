package ca.tetervak.restaurantapp.domain

import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import javax.inject.Inject

class ReloadUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke() = repository.refreshRestaurants()
}
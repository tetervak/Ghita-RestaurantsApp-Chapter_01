package ca.tetervak.restaurantapp.domain

import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import javax.inject.Inject

class ClearUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke() = repository.clearDatabase()
}
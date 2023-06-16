package com.codingtroops.restaurantsapp.domain

import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(id: Int, currentIsFavorite: Boolean) =
        repository.setIsFavoriteById(id, currentIsFavorite.not())
}
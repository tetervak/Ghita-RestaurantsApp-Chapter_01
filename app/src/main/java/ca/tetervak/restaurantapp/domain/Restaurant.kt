package ca.tetervak.restaurantapp.domain

data class Restaurant(
    val id: Int, val title: String, val description: String, val isFavorite: Boolean = false
)
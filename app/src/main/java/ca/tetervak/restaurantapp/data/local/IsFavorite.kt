package ca.tetervak.restaurantapp.data.local

import androidx.room.ColumnInfo

class IsFavorite(
    @ColumnInfo(name = "r_id")
    val id: Int,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)
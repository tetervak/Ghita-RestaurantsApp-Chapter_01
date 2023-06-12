package com.codingtroops.restaurantsapp.model

import androidx.room.ColumnInfo

//@Entity
class IsFavorite(
    @ColumnInfo(name = "r_id")
    val id: Int,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)
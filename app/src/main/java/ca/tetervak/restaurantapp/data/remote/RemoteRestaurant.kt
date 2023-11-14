package ca.tetervak.restaurantapp.data.remote

import com.squareup.moshi.Json

data class RemoteRestaurant(

    @field:Json(name = "r_id")
    val id: Int,

    @field:Json(name ="r_title")
    val title: String,

    @field:Json(name = "r_description")
    val description: String
)
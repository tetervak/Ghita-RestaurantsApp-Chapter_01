package ca.tetervak.restaurantapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRestaurant(

    @SerialName("r_id")
    val id: Int,

    @SerialName("r_title")
    val title: String,

    @SerialName("r_description")
    val description: String
)
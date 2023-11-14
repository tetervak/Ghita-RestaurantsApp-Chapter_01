package ca.tetervak.restaurantapp.data.repository

import ca.tetervak.restaurantapp.data.local.IsFavorite
import ca.tetervak.restaurantapp.data.local.LocalRestaurant
import ca.tetervak.restaurantapp.data.local.RestaurantDao
import ca.tetervak.restaurantapp.data.remote.RemoteRestaurant
import ca.tetervak.restaurantapp.data.remote.RestaurantApi
import ca.tetervak.restaurantapp.domain.Restaurant
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantApi: RestaurantApi,
    private val restaurantDao: RestaurantDao
) : RestaurantRepository {

    override fun getAllRestaurantFlow(): Flow<List<Restaurant>> =
        restaurantDao.getAllRestaurantFlow().map { list -> list.map { it.toRestaurant() } }
            .flowOn(Dispatchers.IO)

    override suspend fun getRestaurantById(id: Int): Restaurant? = withContext(Dispatchers.IO) {
        restaurantDao.getRestaurantById(id)?.toRestaurant()
    }

    override suspend fun toggleIsFavoriteById(id: Int) = withContext(Dispatchers.IO) {
        restaurantDao.toggleIsFavoriteById(id)
    }

    override suspend fun setIsFavoriteById(id: Int, isFavorite: Boolean) =
        withContext(Dispatchers.IO) {
            restaurantDao.setIsFavoriteById(id, isFavorite)
        }

    override suspend fun refreshRestaurants() = withContext(Dispatchers.IO) {

        val restaurants: Deferred<List<LocalRestaurant>> = async {
            restaurantApi.getAllRestaurants().map { it.toLocalRestaurant() }
        }

        val idsOfFavorites: List<Int> = restaurantDao.getIdsOfFavoriteRestaurants()
        val isFavoriteList: List<IsFavorite> =
            idsOfFavorites.map { id -> IsFavorite(id = id, isFavorite = true) }

        restaurantDao.refreshRestaurants(restaurants.await())
        restaurantDao.updateIsFavorite(isFavoriteList)
    }
}

fun LocalRestaurant.toRestaurant(): Restaurant = Restaurant(
    id = this.id, title = this.title, description = this.description, isFavorite = this.isFavorite
)

fun RemoteRestaurant.toLocalRestaurant(): LocalRestaurant = LocalRestaurant(
    id = this.id, title = this.title, description = this.description, isFavorite = false
)
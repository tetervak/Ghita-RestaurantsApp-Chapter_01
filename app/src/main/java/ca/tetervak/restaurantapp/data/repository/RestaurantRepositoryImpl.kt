package ca.tetervak.restaurantapp.data.repository

import ca.tetervak.restaurantapp.data.local.IsFavorite
import ca.tetervak.restaurantapp.data.local.LocalRestaurant
import ca.tetervak.restaurantapp.data.local.RestaurantDao
import ca.tetervak.restaurantapp.data.remote.RemoteRestaurant
import ca.tetervak.restaurantapp.data.remote.RestaurantApi
import ca.tetervak.restaurantapp.domain.Restaurant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(DelicateCoroutinesApi::class)
@Singleton
class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi,
    private val restaurantDao: RestaurantDao,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : RestaurantRepository {

    @Inject
    constructor(
        restaurantApi: RestaurantApi,
        restaurantDao: RestaurantDao
    ) : this(restaurantApi, restaurantDao, GlobalScope, Dispatchers.IO)

    override fun getAllRestaurantFlow(): Flow<List<Restaurant>> =
        restaurantDao.getAllRestaurantFlow().map { list -> list.map { it.toRestaurant() } }
            .flowOn(dispatcher)

    override suspend fun getRestaurantById(id: Int): Restaurant? = withContext(dispatcher) {
        restaurantDao.getRestaurantById(id)?.toRestaurant()
    }

    override suspend fun toggleIsFavoriteById(id: Int) = withContext(dispatcher) {
        restaurantDao.toggleIsFavoriteById(id)
    }

    override suspend fun setIsFavoriteById(id: Int, isFavorite: Boolean) =
        withContext(dispatcher) {
            restaurantDao.setIsFavoriteById(id, isFavorite)
        }

    override suspend fun refreshRestaurants() {
        externalScope.launch(dispatcher) {

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
}

fun LocalRestaurant.toRestaurant(): Restaurant = Restaurant(
    id = this.id, title = this.title, description = this.description, isFavorite = this.isFavorite
)

fun RemoteRestaurant.toLocalRestaurant(): LocalRestaurant = LocalRestaurant(
    id = this.id, title = this.title, description = this.description, isFavorite = false
)
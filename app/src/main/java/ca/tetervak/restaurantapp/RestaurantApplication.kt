package ca.tetervak.restaurantapp

import android.app.Application
import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class RestaurantApplication : Application() {

    @Inject
    lateinit var repository: RestaurantRepository

    companion object {
        private val mainScope = MainScope()
    }

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    override fun onCreate() {
        super.onCreate()
        mainScope.launch(errorHandler) {
            repository.refreshRestaurants()
        }
    }
}
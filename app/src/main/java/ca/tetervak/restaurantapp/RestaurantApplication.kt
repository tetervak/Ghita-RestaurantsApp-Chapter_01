package ca.tetervak.restaurantapp

import android.app.Application
import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class RestaurantApplication : Application(){

    @Inject
    lateinit var repository: RestaurantRepository

    override fun onCreate() {
        super.onCreate()

        MainScope().launch(Dispatchers.IO) {
            repository.refreshRestaurants()
        }
    }
}
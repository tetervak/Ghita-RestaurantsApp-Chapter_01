package ca.tetervak.restaurantapp.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

@HiltWorker
class RefreshWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: RestaurantRepository
) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "ca.tetervak.restaurant.workmanager.RefreshWorker"
        const val MAX_ATTEMPT_COUNT = 3
    }
    override suspend fun doWork(): Result {

        try {
            repository.refreshRestaurants()
        } catch (e: HttpException) {
            return if(runAttemptCount < MAX_ATTEMPT_COUNT){
                Result.retry()
            }else{
                Result.failure()
            }
        }

        return Result.success()
    }
}
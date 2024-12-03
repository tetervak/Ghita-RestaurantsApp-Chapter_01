package ca.tetervak.restaurantapp.workmanager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

fun setupRefreshWork(context: Context) {

    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(false)
        .setRequiresBatteryNotLow(true)
        .build()

    // it is not allowed be more often than every 15 min
    val repeatingRequest = PeriodicWorkRequestBuilder<RefreshWorker>(
        repeatInterval = 15,
        repeatIntervalTimeUnit = TimeUnit.MINUTES
    ).setConstraints(constraints).build()

    val workManager = WorkManager.getInstance(context)

    //val oneTimeWorkRequest = OneTimeWorkRequestBuilder<RefreshWorker>().build()
    //workManager.enqueue(oneTimeWorkRequest)

    workManager.enqueueUniquePeriodicWork(
        RefreshWorker.WORK_NAME,
        ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
        repeatingRequest
    )
}
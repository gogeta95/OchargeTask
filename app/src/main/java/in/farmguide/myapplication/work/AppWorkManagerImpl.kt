package `in`.farmguide.myapplication.work

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppWorkManagerImpl @Inject constructor() : AppWorkManager {

    override fun fetchAndSavePosts() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val work = OneTimeWorkRequest.Builder(FetchPostsWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueue(work)
    }
}
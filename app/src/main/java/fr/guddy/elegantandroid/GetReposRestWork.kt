package fr.guddy.elegantandroid

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import okhttp3.OkHttpClient

class GetReposRestWork(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        try {
            val data = GetReposRestWorkData(inputData)
            val dbHelper = ElegantAndroidDbHelper(applicationContext, listOf(RepoTable()))
            val repos = RestReposByUser(
                client = OkHttpClient(),
                baseUrl = data.baseUrl,
                user = data.user
            )
            DbRepos(
                db = dbHelper.writableDatabase
            ).addAll(
                repos
            )
            return Result.success()
        } catch (exception: Exception) {
            return Result.failure()
        }
    }
}
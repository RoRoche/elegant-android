package fr.guddy.elegantandroid.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import fr.guddy.elegantandroid.persistence.DbRepos
import fr.guddy.elegantandroid.persistence.ElegantAndroidDbHelper
import fr.guddy.elegantandroid.persistence.RepoTable
import fr.guddy.elegantandroid.rest.RestReposByUser
import okhttp3.OkHttpClient

class GetReposRestWork(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        try {
            val data = GetReposRestWorkData(
                inputData
            )
            val dbHelper = ElegantAndroidDbHelper(
                applicationContext,
                listOf(
                    RepoTable()
                )
            )
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
            dbHelper.close()
            return Result.success()
        } catch (exception: Exception) {
            return Result.failure()
        }
    }
}
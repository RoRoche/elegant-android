package fr.guddy.elegantandroid.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import fr.guddy.elegantandroid.persistence.DbRepo
import fr.guddy.elegantandroid.persistence.ElegantAndroidDbHelper
import fr.guddy.elegantandroid.persistence.RepoTable
import fr.guddy.elegantandroid.rest.RestReposByUser
import fr.guddy.eoandroidpersistence.queries.BulkInsert
import okhttp3.OkHttpClient

class GetReposRestWork(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val dbHelper = ElegantAndroidDbHelper(
            applicationContext,
            listOf(
                RepoTable()
            )
        )
        try {
            val data = GetReposRestWorkData(
                inputData
            )
            val repos = RestReposByUser(
                client = OkHttpClient(),
                baseUrl = data.baseUrl,
                user = data.user
            )
            val result = BulkInsert(
                db = dbHelper.writableDatabase,
                into = RepoTable(),
                values = repos.map { DbRepo(it) }
            ).result()
            return if (result) {
                Result.success()
            } else {
                Result.failure()
            }
        } catch (exception: Exception) {
            return Result.failure()
        } finally {
            dbHelper.close()
        }
    }
}
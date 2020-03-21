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

/**
 * Class representing a [Worker] to get repos for user from a REST API.
 *
 * @param appContext The [Context] to use.
 * @param workerParams The [WorkerParameters] passed to the work.
 */
class GetReposForUserRestWork(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    /**
     * The default method to implement to perform the work.
     * It gets data from a REST API and stores it into SQLite database.
     *
     * @return The [Result] of the performed work.
     */
    override fun doWork(): Result {
        val dbHelper = ElegantAndroidDbHelper(
            applicationContext,
            listOf(
                RepoTable()
            )
        )
        try {
            val data = GetReposForUserRestWorkData(
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
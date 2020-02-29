package fr.guddy.elegantandroid

import androidx.work.ListenableWorker
import androidx.work.WorkManager
import fr.guddy.elegantandroid.workmanager.ManagedRestRequest
import fr.guddy.elegantandroid.workmanager.OneTimeRestWorkRequest

class GetReposManagedRestRequest(
    origin: ManagedRestRequest
) : ManagedRestRequest.Wrap(origin) {
    constructor(
        workManager: WorkManager,
        workerClass: Class<out ListenableWorker?>,
        data: GetReposRestWorkData
    ) : this(
        OneTimeRestWorkRequest(
            workManager,
            workerClass,
            data.toData()
        )
    )

    constructor(
        workManager: WorkManager,
        baseUrl: String,
        user: String
    ) : this(
        workManager,
        GetReposRestWork::class.java,
        GetReposRestWorkData(
            baseUrl = baseUrl,
            user = user
        )
    )
}
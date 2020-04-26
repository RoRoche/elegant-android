package com.github.roroche.elegantandroid.workmanager

import androidx.work.ListenableWorker
import androidx.work.WorkManager
import com.github.roroche.eoandroidworkmanager.InputData
import com.github.roroche.eoandroidworkmanager.ManagedRestRequest
import com.github.roroche.eoandroidworkmanager.OneTimeRestWorkRequest

/**
 * Class representing a [ManagedRestRequest] to get repos for a given user.
 *
 * @param origin The delegate [ManagedRestRequest].
 */
class GetReposForUserManagedRestRequest(
    origin: ManagedRestRequest
) : ManagedRestRequest.Wrap(origin) {

    /**
     * Secondary constructor to build a [OneTimeRestWorkRequest].
     *
     * @param workManager The [WorkManager] instance to use to perform the work.
     * @param workerClass The [Class] corresponding to the work to perform.
     * @param data The [InputData] to pass to the work.
     */
    constructor(
        workManager: WorkManager,
        workerClass: Class<out ListenableWorker?>,
        data: InputData
    ) : this(
        OneTimeRestWorkRequest(
            workManager,
            workerClass,
            data.toData()
        )
    )

    /**
     * Secondary constructor to build a [GetReposForUserRestWork].
     *
     * @param workManager The [WorkManager] instance to use to perform the work.
     * @param baseUrl The REST API base URL.
     * @param user The user for whom to get repos.
     */
    constructor(
        workManager: WorkManager,
        baseUrl: String,
        user: String
    ) : this(
        workManager,
        GetReposForUserRestWork::class.java,
        GetReposForUserRestWorkData(
            baseUrl = baseUrl,
            user = user
        )
    )
}

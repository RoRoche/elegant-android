package fr.guddy.elegantandroid.workmanager

import androidx.work.*
import fr.guddy.elegantandroid.workmanager.ManagedRestRequest

class OneTimeRestWorkRequest(
    private val workManager: WorkManager,
    private val worker: OneTimeWorkRequest,
    private val operation: Operation
) : ManagedRestRequest {

    constructor(
        workManager: WorkManager,
        worker: OneTimeWorkRequest
    ) : this(
        workManager,
        worker,
        workManager.enqueue(worker)
    )

    constructor(
        workManager: WorkManager,
        workerClass: Class<out ListenableWorker?>,
        data: Data,
        constraints: Constraints
    ) : this(
        workManager,
        OneTimeWorkRequest.Builder(workerClass)
            .setInputData(data)
            .setConstraints(constraints)
            .build()
    )

    constructor(
        workManager: WorkManager,
        workerClass: Class<out ListenableWorker?>,
        data: Data
    ) : this(
        workManager,
        workerClass,
        data,
        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    )

    override fun operation() = operation

    override fun liveData() = workManager.getWorkInfoByIdLiveData(worker.id)

    override fun cancel() = workManager.cancelWorkById(worker.id)
}
package fr.guddy.eoandroidworkmanager

import androidx.lifecycle.LiveData
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.Operation
import androidx.work.WorkManager

/**
 * Class describing a REST request executing one time.
 *
 * @property workManager The [WorkManager] used to perform the request.
 * @property worker The [OneTimeWorkRequest] to be performed.
 * @property operation The [Operation] describing the request.
 */
class OneTimeRestWorkRequest(
    private val workManager: WorkManager,
    private val worker: OneTimeWorkRequest,
    private val operation: Operation
) : ManagedRestRequest {

    /**
     * Secondary constructor that enqueues the [OneTimeWorkRequest] into the [WorkManager].
     *
     * @param workManager The [WorkManager] used to perform the request.
     * @property worker The [OneTimeWorkRequest] to be performed.
     */
    constructor(
        workManager: WorkManager,
        worker: OneTimeWorkRequest
    ) : this(
        workManager,
        worker,
        workManager.enqueue(worker)
    )

    /**
     * Secondary constructor that builds the [OneTimeWorkRequest] to be enqueued.
     *
     * @param workManager The [WorkManager] used to perform the request.
     * @param workerClass The [Class] of the work request to be performed.
     * @param data The [Data] to be passed to the work request.
     * @param constraints The [Constraints] to be considered to perform the work request.
     */
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

    /**
     * Secondary constructor that build default [Constraints] to be considered to execute to work
     * request. The default [Constraints] is to require connected network.
     *
     * @param workManager The [WorkManager] used to perform the request.
     * @param workerClass The [Class] of the work request to be performed.
     * @param data The [Data] to be passed to the work request.
     */
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

    /**
     * @return The WorkManager's operation corresponding the REST request.
     */
    override fun operation() = operation

    /**
     * @return [LiveData] to be notified of the REST request execution status, from the worker's ID.
     */
    override fun liveData() = workManager.getWorkInfoByIdLiveData(worker.id)

    /**
     * Cancel the REST request by its worker's ID.
     *
     * @return An {@link Operation} that can be used to determine when the cancelWorkById has
     * completed.
     */
    override fun cancel() = workManager.cancelWorkById(worker.id)
}

package com.github.roroche.eoandroidworkmanager

import androidx.lifecycle.LiveData
import androidx.work.Operation
import androidx.work.WorkInfo

/**
 * Interface describing a REST request that can be performed and managed by WorkManager.
 */
interface ManagedRestRequest {
    /**
     * @return The WorkManager's operation corresponding the REST request.
     */
    fun operation(): Operation

    /**
     * @return [LiveData] to be notified of the REST request execution status.
     */
    fun liveData(): LiveData<WorkInfo>

    /**
     * Cancel the REST request.
     *
     * @return An {@link Operation} that can be used to determine when the cancelWorkById has
     * completed.
     */
    fun cancel(): Operation

    /**
     * Convenient class that wrap a [ManagedRestRequest].
     *
     * @property origin The delegate [ManagedRestRequest].
     */
    abstract class Wrap protected constructor(
        private val origin: ManagedRestRequest
    ) : ManagedRestRequest by origin
}

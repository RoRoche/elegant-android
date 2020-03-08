package fr.guddy.eoandroidworkmanager

import androidx.lifecycle.LiveData
import androidx.work.Operation
import androidx.work.WorkInfo

interface ManagedRestRequest {
    fun operation(): Operation
    fun liveData(): LiveData<WorkInfo>
    fun cancel(): Operation

    abstract class Wrap protected constructor(
        private val origin: ManagedRestRequest
    ) : ManagedRestRequest by origin
}
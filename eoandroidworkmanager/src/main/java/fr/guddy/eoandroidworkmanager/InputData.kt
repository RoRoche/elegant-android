package fr.guddy.eoandroidworkmanager

import androidx.work.Data

/**
 * Interface describing data that can be passed to a WorkManager's Job.
 */
interface InputData {
    /**
     * Convert data into a specific [Data] object.
     */
    fun toData(): Data
}
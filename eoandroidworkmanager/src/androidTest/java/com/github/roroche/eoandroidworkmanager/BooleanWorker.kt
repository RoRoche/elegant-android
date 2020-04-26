package com.github.roroche.eoandroidworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Simple Worker returning expected result passed in Data.
 */
class BooleanWorker(
    context: Context,
    parameters: WorkerParameters
) : Worker(context, parameters) {
    override fun doWork(): Result {
        val booleanInputData = BooleanInputData(inputData)
        return if (booleanInputData.success) {
            Result.success(inputData)
        } else {
            Result.failure()
        }
    }
}

package com.github.roroche.eoandroidworkmanager

import androidx.work.Data
import androidx.work.workDataOf

/**
 * Simple Data containing a boolean value.
 */
class BooleanInputData(
    val success: Boolean
) : InputData {

    constructor(data: Data) : this(
        success = data.getBoolean("success", false)
    )

    override fun toData() = workDataOf(
        "success" to success
    )
}

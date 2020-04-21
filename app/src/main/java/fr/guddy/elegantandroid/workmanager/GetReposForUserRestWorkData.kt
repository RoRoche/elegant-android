package fr.guddy.elegantandroid.workmanager

import androidx.work.Data
import androidx.work.workDataOf
import fr.guddy.eoandroidworkmanager.InputData

/**
 * Class representing [InputData] to pass to the work that gets repos for user from a REST API.
 *
 * @property baseUrl The REST API base URL.
 * @property user The user for whom to get repos.
 */
class GetReposForUserRestWorkData(
    val baseUrl: String,
    val user: String
) : InputData {

    /**
     * Secondary constructor that get data from a [Data] object.
     *
     * @param data The [Data] object where to find [InputData].
     */
    constructor(data: Data) : this(
        baseUrl = data.getString("baseUrl")!!,
        user = data.getString("user")!!
    )

    /**
     * Convert data into a specific [Data] object.
     */
    override fun toData() = workDataOf(
        "baseUrl" to baseUrl,
        "user" to user
    )
}

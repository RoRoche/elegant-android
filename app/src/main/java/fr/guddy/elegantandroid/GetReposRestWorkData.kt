package fr.guddy.elegantandroid

import androidx.work.Data
import androidx.work.workDataOf
import fr.guddy.eoandroidworkmanager.InputData

class GetReposRestWorkData(
    val baseUrl: String,
    val user: String
) : InputData {

    constructor(data: Data) : this(
        baseUrl = data.getString("baseUrl")!!,
        user = data.getString("user")!!
    )

    override fun toData() = workDataOf(
        "baseUrl" to baseUrl,
        "user" to user
    )
}
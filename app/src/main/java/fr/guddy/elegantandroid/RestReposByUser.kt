package fr.guddy.elegantandroid

import fr.guddy.eorest.SuccessfulRestRequest
import fr.guddy.eorest.WithBodyRestRequest
import okhttp3.OkHttpClient

class RestReposByUser(private val repos: List<Repo>) : List<Repo> by repos {
    constructor(
        client: OkHttpClient,
        baseUrl: String,
        user: String
    ) : this(
        JsonRepos(
            WithBodyRestRequest(
                SuccessfulRestRequest(
                    GetReposRestRequest(
                        client = client,
                        baseUrl = baseUrl,
                        user = user
                    )
                )
            )
        )
    )
}
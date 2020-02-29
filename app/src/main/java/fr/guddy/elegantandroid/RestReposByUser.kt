package fr.guddy.elegantandroid

import fr.guddy.elegantandroid.rest.SuccessfulRestRequest
import fr.guddy.elegantandroid.rest.WithBodyRestRequest
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
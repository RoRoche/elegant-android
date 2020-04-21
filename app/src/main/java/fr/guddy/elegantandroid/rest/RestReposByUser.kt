package fr.guddy.elegantandroid.rest

import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.elegantandroid.json.JsonRepos
import fr.guddy.elegantandroid.rest.requests.GetReposForUserRestRequest
import fr.guddy.eorest.requests.SuccessfulRestRequest
import fr.guddy.eorest.requests.WithBodyRestRequest
import okhttp3.OkHttpClient

/**
 * Class representing a [List] of [Repo] for a given user, fetched from a REST API.
 *
 * @property repos The delegate [List] of [Repo].
 */
class RestReposByUser(
    private val repos: List<Repo>
) : List<Repo> by repos {

    /**
     * Secondary constructor that builds and performs a [GetReposForUserRestRequest] to get repos
     * from a REST API.
     *
     * @param client The [OkHttpClient] to use.
     * @param baseUrl The REST API base URL.
     * @param user The user for whom to get repos.
     */
    constructor(
        client: OkHttpClient,
        baseUrl: String,
        user: String
    ) : this(
        JsonRepos(
            WithBodyRestRequest(
                SuccessfulRestRequest(
                    GetReposForUserRestRequest(
                        client = client,
                        baseUrl = baseUrl,
                        user = user
                    )
                )
            )
        )
    )
}

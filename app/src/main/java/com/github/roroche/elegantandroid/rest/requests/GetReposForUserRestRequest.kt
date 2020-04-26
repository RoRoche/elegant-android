package com.github.roroche.elegantandroid.rest.requests

import com.github.roroche.eorest.requests.RestRequest
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Class representing a [RestRequest] to get repos form GitHub API for a given user.
 *
 * @param origin The delegate [RestRequest].
 */
class GetReposForUserRestRequest(
    origin: RestRequest
) : RestRequest.Wrap(origin) {

    /**
     * Secondary constructor to build the [Request] to perform.
     *
     * @param client The [OkHttpClient] to use to perform the [Request].
     * @param baseUrl The REST API base URL.
     * @param user The user for whom to get repos.
     */
    constructor(
        client: OkHttpClient,
        baseUrl: String,
        user: String
    ) : this(
        RestRequest.WithOkHttp(
            client,
            Request.Builder()
                .url(
                    baseUrl.toHttpUrl()
                        .newBuilder()
                        .addPathSegment("users")
                        .addPathSegment(user)
                        .addPathSegment("repos")
                        .build()
                )
                .build()
        )
    )
}

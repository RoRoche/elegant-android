package fr.guddy.elegantandroid

import fr.guddy.eorest.RestRequest
import okhttp3.Call
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class GetReposRestRequest(
    private val call: Call
) : RestRequest {

    constructor(client: OkHttpClient, baseUrl: String, user: String) : this(
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

    constructor(client: OkHttpClient, request: Request) : this(
        client.newCall(request)
    )

    override fun response(): Response {
        return call.execute()
    }

    override fun cancel() {
        if (!call.isCanceled()) {
            call.cancel()
        }
    }
}
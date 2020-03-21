package fr.guddy.eorest.requests

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * Interface describing the way to perform a REST request (based on OkHttp).
 */
interface RestRequest {
    /**
     * @return The response of the REST request.
     */
    fun response(): Response

    /**
     * Cancel the pending REST request.
     */
    fun cancel()

    /**
     * Convenient class to wrap a REST request and delegate its methods.
     * It allows to easily compose it using the Decorator pattern.
     *
     * @property origin The REST request to delegate to.
     */
    abstract class Wrap protected constructor(
        private val origin: RestRequest
    ) : RestRequest by origin

    /**
     * Convenient class to wrap a OkHttp's [Call] and perform it.
     *
     * @property call The [Call] to perform.
     */
    class WithOkHttp constructor(
        private val call: Call
    ) : RestRequest {

        /**
         * Secondary constructor to build a [Call].
         *
         * @param client The [OkHttpClient] to use to perform the [Request].
         * @param request The [Request] to perform.
         */
        constructor(client: OkHttpClient, request: Request) : this(
            client.newCall(request)
        )

        /**
         * @return The response of the REST request.
         */
        override fun response(): Response {
            return call.execute()
        }

        /**
         * Cancel the pending REST request.
         */
        override fun cancel() {
            if (!call.isCanceled()) {
                call.cancel()
            }
        }
    }
}
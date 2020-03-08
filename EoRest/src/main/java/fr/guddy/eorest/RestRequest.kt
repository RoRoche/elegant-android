package fr.guddy.eorest

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
}
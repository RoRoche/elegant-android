package fr.guddy.eorest

import fr.guddy.eorest.exceptions.RestRequestFailureException
import okhttp3.Response

/**
 * Decorator class to validate that the REST request is successful.
 *
 * @param origin The original REST request to perform.
 */
class SuccessfulRestRequest(
    origin: RestRequest
) : RestRequest.Wrap(origin) {
    /**
     * @return The response if it is successful.
     * @throws RestRequestFailureException if response is not successful.
     */
    override fun response(): Response {
        val response = super.response()
        if (response.isSuccessful) {
            return response
        } else {
            throw RestRequestFailureException(
                response
            )
        }
    }
}
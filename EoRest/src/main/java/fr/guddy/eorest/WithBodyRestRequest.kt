package fr.guddy.eorest

import fr.guddy.eorest.exceptions.NoBodyRestResponseException
import okhttp3.Response

/**
 * Decorator class to check that the response contains a body.
 *
 * @param origin The REST request to perform.
 */
class WithBodyRestRequest(
    origin: RestRequest
) : RestRequest.Wrap(origin) {
    /**
     * @return The response if it contains a body.
     * @throws NoBodyRestResponseException if response does not contain a body.
     */
    override fun response(): Response {
        val response = super.response()
        if (response.body != null) {
            return response
        } else {
            throw NoBodyRestResponseException(response)
        }
    }
}
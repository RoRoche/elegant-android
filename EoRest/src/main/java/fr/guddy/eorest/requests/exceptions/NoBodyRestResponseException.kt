package fr.guddy.eorest.requests.exceptions

import okhttp3.Response

/**
 * Exception that is thrown when a response does not contain body.
 *
 * @property response The response that has no body.
 */
class NoBodyRestResponseException(
    val response: Response
) : RuntimeException("REST request does not have body: $response")

package fr.guddy.eorest.exceptions

import okhttp3.Response

/**
 * Exception that is thrown when a response is not successful.
 *
 * @property response The response that is not successful.
 */
class RestRequestFailureException(
    val response: Response
) : RuntimeException("REST request failed with response $response")
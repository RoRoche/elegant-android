package fr.guddy.eorest.exceptions

import okhttp3.Response

class RestRequestFailureException(
    val response: Response
) : RuntimeException("REST request failed with response $response")
package fr.guddy.elegantandroid.rest

import fr.guddy.elegantandroid.rest.RestRequest
import fr.guddy.elegantandroid.rest.exceptions.RestRequestFailureException
import okhttp3.Response

class SuccessfulRestRequest(origin: RestRequest) : RestRequest.Wrap(origin) {
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
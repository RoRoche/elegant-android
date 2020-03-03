package fr.guddy.eorest

import fr.guddy.eorest.exceptions.NoBodyRestResponseException
import okhttp3.Response

class WithBodyRestRequest(origin: RestRequest) : RestRequest.Wrap(origin) {
    override fun response(): Response {
        val response = super.response()
        if (response.body != null) {
            return response
        } else {
            throw NoBodyRestResponseException()
        }
    }
}
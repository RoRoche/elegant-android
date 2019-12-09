package fr.guddy.elegantandroid

import okhttp3.Response

interface RestRequest {
    fun response(): Response

    fun cancel()

    abstract class Wrap protected constructor(
        private val origin: RestRequest
    ) : RestRequest by origin
}
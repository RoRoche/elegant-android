package fr.guddy.elegantandroid.rest.exceptions

import okhttp3.Response
import java.lang.RuntimeException

class RestRequestFailureException(val response: Response) : RuntimeException("REST request failed")
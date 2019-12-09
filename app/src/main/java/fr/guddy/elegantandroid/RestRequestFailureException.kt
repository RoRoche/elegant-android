package fr.guddy.elegantandroid

import okhttp3.Response
import java.lang.RuntimeException

class RestRequestFailureException(val response: Response) : RuntimeException("REST request failed")
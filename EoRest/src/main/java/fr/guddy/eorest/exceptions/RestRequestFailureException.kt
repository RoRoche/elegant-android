package fr.guddy.eorest.exceptions

import okhttp3.Response
import java.lang.RuntimeException

class RestRequestFailureException(val response: Response) : RuntimeException("REST request failed")
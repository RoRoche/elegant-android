package fr.guddy.elegantandroid.rest.exceptions

import java.lang.RuntimeException

class NoBodyRestResponseException : RuntimeException("REST request does not have body")

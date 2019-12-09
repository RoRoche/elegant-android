package fr.guddy.elegantandroid

import java.lang.RuntimeException

class NoBodyRestResponseException : RuntimeException("REST request does not have body")

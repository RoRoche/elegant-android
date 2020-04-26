package com.github.roroche.elegantandroid.json

import com.github.roroche.elegantandroid.domain.Repo
import com.github.roroche.eorest.requests.RestRequest
import okhttp3.Response
import org.json.JSONArray

/**
 * Class representing a list of [Repo] in JSON.
 *
 * @property repos The [List] of repos to use as a delegate.
 */
class JsonRepos(
    private val repos: List<Repo>
) : List<Repo> by repos {

    /**
     * Secondary constructor to build [JSONArray] from a raw [String].
     *
     * @param json The raw [String] containing JSON data.
     */
    constructor(json: String) : this(JSONArray(json))

    /**
     * Secondary constructor to deal with a [JSONArray] containing the repos.
     *
     * @param jsonArray [JSONArray] containing the repos.
     */
    constructor(jsonArray: JSONArray) : this(
        (0 until jsonArray.length())
            .map(jsonArray::getJSONObject)
            .map { JsonRepo(it) }
    )

    /**
     * Secondary constructor that performs a [RestRequest] to get repos in JSON.
     *
     * @param restRequest The [RestRequest] to perform.
     */
    constructor(
        restRequest: RestRequest
    ) : this(restRequest.response())

    /**
     * Secondary constructor that deals with [Response]
     * to get a raw string containing repos as JSON.
     */
    constructor(response: Response) : this(
        response.body!!.string()
    )
}

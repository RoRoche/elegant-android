package fr.guddy.elegantandroid

import fr.guddy.elegantandroid.rest.RestRequest
import okhttp3.Response
import org.json.JSONArray

class JsonRepos(private val repos: List<Repo>) : List<Repo> by repos {
    constructor(json: String) : this(JSONArray(json))

    constructor(jsonArray: JSONArray) : this(
        (0 until jsonArray.length())
            .map(jsonArray::getJSONObject)
            .map { JsonRepo(it) }
    )

    constructor(restRequest: RestRequest) : this(restRequest.response())

    constructor(response: Response) : this(
        response.body!!.string()
    )
}
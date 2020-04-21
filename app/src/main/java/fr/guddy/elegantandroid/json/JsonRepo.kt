package fr.guddy.elegantandroid.json

import fr.guddy.elegantandroid.domain.Repo
import org.json.JSONObject

/**
 * Class representing a [Repo] parsed from JSON.
 *
 * @property jsonObject The [JSONObject] containing data.
 */
class JsonRepo(
    private val jsonObject: JSONObject
) : Repo {

    /**
     * @return The repo's ID.
     */
    override fun id(): Long = jsonObject.getLong("id")

    /**
     * @return The repo's name.
     */
    override fun name(): String = jsonObject.getString("name")

    /**
     * @return The repo's description.
     */
    override fun description(): String = jsonObject.getString("description")

    /**
     * @return The repo's url.
     */
    override fun url(): String = jsonObject.getString("url")

    /**
     * @return The repo's owner.
     */
    override fun owner(): String = jsonObject.getJSONObject("owner").getString("login")
}

package fr.guddy.elegantandroid

import org.json.JSONObject

class JsonRepo(private val jsonObject: JSONObject) : Repo {

    override fun id(): Long = jsonObject.getLong("id")

    override fun name(): String = jsonObject.getString("name")

    override fun description(): String = jsonObject.getString("description")

    override fun url(): String = jsonObject.getString("url")

    override fun owner(): String = jsonObject.getJSONObject("owner").getString("login")
}
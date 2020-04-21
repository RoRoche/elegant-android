package fr.guddy.elegantandroid.persistence

import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.eoandroidpersistence.Table

/**
 * The [Table] containing [Repo].
 */
class RepoTable : Table {

    /**
     * @return The table's name.
     */
    override fun name() = "REPO"

    /**
     * @return The columns composing the table.
     */
    override fun columns(): List<String> = listOf(
        "id",
        "name",
        "description",
        "url",
        "owner"
    )
}

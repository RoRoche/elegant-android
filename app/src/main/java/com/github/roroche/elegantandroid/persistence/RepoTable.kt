package com.github.roroche.elegantandroid.persistence

import com.github.roroche.elegantandroid.domain.Repo
import com.github.roroche.eoandroidpersistence.Table

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

package fr.guddy.elegantandroid.persistence

import android.database.sqlite.SQLiteDatabase
import fr.guddy.eoandroidpersistence.Table
import fr.guddy.elegantandroid.domain.Repo

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

    /**
     * Create the table in the database.
     *
     * @param db The database in which the table must be created.
     */
    override fun create(db: SQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE IF NOT EXISTS `${name()}` (
                    `id` INTEGER PRIMARY KEY NOT NULL, 
                    `name` TEXT NOT NULL, 
                    `description` TEXT NOT NULL,
                    `url` TEXT NOT NULL,
                    `owner` TEXT NOT NULL
                )
                """
        )
    }

    /**
     * Drop the table in the database.
     *
     * @param db The database in which the table must be dropped.
     */
    override fun drop(db: SQLiteDatabase) {
        db.execSQL(
            """
                DROP TABLE IF EXISTS ${name()}
            """
        )
    }
}
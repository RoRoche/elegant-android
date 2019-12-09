package fr.guddy.elegantandroid

import android.database.sqlite.SQLiteDatabase

class RepoTable : Table {
    override fun name() = "REPO"

    override fun columns(): List<String> = listOf(
        "id",
        "name",
        "description",
        "url",
        "owner"
    )

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

    override fun drop(db: SQLiteDatabase) {
        db.execSQL(
            """
                DROP TABLE IF EXISTS ${name()}
            """
        )
    }
}
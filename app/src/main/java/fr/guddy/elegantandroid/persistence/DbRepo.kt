package fr.guddy.elegantandroid.persistence

import android.content.ContentValues
import android.database.Cursor
import fr.guddy.elegantandroid.domain.Repo
import fr.guddy.elegantandroid.domain.SimpleRepo
import fr.guddy.eoandroidpersistence.Persistable

/**
 * Class representing [Repo] fetched from database.
 *
 * @param origin The delegate [Repo].
 */
class DbRepo(
    origin: Repo
) : Repo.Wrap(origin), Persistable {

    /**
     * Secondary constructor that build a [SimpleRepo] with values from a [Cursor].
     *
     * @param cursor The [Cursor] containing data.
     */
    constructor(cursor: Cursor) : this(
        SimpleRepo(
            id = cursor.getLong(cursor.getColumnIndexOrThrow(ID)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(NAME)),
            description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)),
            url = cursor.getString(cursor.getColumnIndexOrThrow(URL)),
            owner = cursor.getString(cursor.getColumnIndexOrThrow(OWNER))
        )
    )

    /**
     * @return [Repo] as [ContentValues] to be stored in SQLite database.
     */
    override fun toContentValues() = ContentValues().apply {
        put(ID, id())
        put(NAME, name())
        put(DESCRIPTION, description())
        put(URL, url())
        put(OWNER, owner())
    }

    companion object Columns {
        private const val ID = "id"
        private const val NAME = "name"
        private const val DESCRIPTION = "description"
        private const val URL = "url"
        private const val OWNER = "owner"
    }
}
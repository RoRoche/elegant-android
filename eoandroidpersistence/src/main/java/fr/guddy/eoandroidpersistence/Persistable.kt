package fr.guddy.eoandroidpersistence

import android.content.ContentValues

/**
 * Interface that describes an object that can be persisted in SQLite database.
 */
interface Persistable {
    /**
     * @return Object as [ContentValues] to be stored in SQLite database.
     */
    fun toContentValues() : ContentValues
}
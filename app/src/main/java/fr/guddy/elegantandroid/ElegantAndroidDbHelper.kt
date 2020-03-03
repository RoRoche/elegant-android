package fr.guddy.elegantandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import fr.guddy.eoandroidpersistence.Table

class ElegantAndroidDbHelper(
    context: Context,
    private val tables: List<Table>
): SQLiteOpenHelper(
    context,
    NAME,
    null,
    VERSION
) {
    companion object {
        private const val VERSION = 1
        private const val NAME = "ElegantAndroid.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        tables.forEach {
            it.create(db!!)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        tables.forEach {
            it.drop(db!!)
        }
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}
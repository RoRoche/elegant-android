package fr.guddy.elegantandroid

import android.database.sqlite.SQLiteDatabase

interface Table {
    fun name(): String

    fun columns(): List<String>

    fun create(db: SQLiteDatabase)

    fun drop(db: SQLiteDatabase)
}
package fr.guddy.elegantandroid.persistence

import android.content.ContentValues

interface Persistable {
    fun toContentValues() : ContentValues
}
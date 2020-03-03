package fr.guddy.eoandroidpersistence

import android.content.ContentValues

interface Persistable {
    fun toContentValues() : ContentValues
}
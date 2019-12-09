package fr.guddy.elegantandroid

import android.content.ContentValues

interface Persistable {
    fun toContentValues() : ContentValues
}
package fr.guddy.eoandroidworkmanager

import androidx.work.Data

interface InputData {
    fun toData(): Data
}
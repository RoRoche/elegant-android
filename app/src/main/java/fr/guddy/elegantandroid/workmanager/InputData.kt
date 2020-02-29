package fr.guddy.elegantandroid.workmanager

import androidx.work.Data

interface InputData {
    fun toData(): Data
}
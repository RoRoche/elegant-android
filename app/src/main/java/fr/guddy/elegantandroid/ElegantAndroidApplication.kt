package fr.guddy.elegantandroid

import android.app.Application

//import leakcanary.AppWatcher

class ElegantAndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        AppWatcher.config = AppWatcher.config.copy(watchFragmentViews = false)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}
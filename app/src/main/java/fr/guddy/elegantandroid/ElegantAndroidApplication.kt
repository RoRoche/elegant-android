package fr.guddy.elegantandroid

import android.app.Application

//import leakcanary.AppWatcher

/**
 * The class representing the [Application].
 */
class ElegantAndroidApplication : Application() {

    /**
     * Called when the application is starting
     */
    override fun onCreate() {
        super.onCreate()
//        AppWatcher.config = AppWatcher.config.copy(watchFragmentViews = false)
    }
}
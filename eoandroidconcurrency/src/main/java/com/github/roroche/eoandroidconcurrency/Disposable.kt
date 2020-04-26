package com.github.roroche.eoandroidconcurrency

/**
 * Interface defining the contract of object that must be disposed.
 * It useful to avoid memory leaks.
 * That's a place to release resources and clear WeakReference for example.
 */
interface Disposable {
    /**
     * Method that will be called when disposing resources is necessary.
     */
    fun dispose()
}

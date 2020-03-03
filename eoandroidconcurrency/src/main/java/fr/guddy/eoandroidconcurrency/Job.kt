package fr.guddy.eoandroidconcurrency

interface Job: Disposable {
    fun run()

    open class Async(private val thread: Thread): Job {

        constructor(runnable: Runnable): this(Thread(runnable))

        override fun run() {
            thread.start()
        }

        override fun dispose() {
        }
    }
}
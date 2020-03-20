package fr.guddy.eoandroidconcurrency

/**
 * Interface contracting the way to run a specific job.
 */
interface Job : Disposable {
    /**
     * The method to be called to run the job.
     */
    fun run()

    /**
     * Convenient class to perform a job asynchronously.
     *
     * @param thread The [Thread] on which to perform the job.
     */
    abstract class Async(private val thread: Thread) : Job {

        /**
         * Secondary constructor to ease the call of the primary one.
         *
         * @param runnable The [Runnable] to be perform asynchronously.
         */
        constructor(runnable: Runnable) : this(Thread(runnable))

        /**
         * The method to be called to run the job.
         * It basically calls the start method of the [Thread].
         */
        override fun run() {
            thread.start()
        }
    }
}

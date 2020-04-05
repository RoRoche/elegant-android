package fr.guddy.eoandroidconcurrency

import android.os.Looper
import org.amshove.kluent.shouldBe
import org.junit.Test

/**
 * Tests of the [Callback] interface.
 */
class CallbackTest {

    private class ThreadedCallback : Callback<Int> {
        private lateinit var thread: Thread

        override fun accept(data: Int) {
            this.thread = Thread.currentThread()
        }

        override fun dispose() {
            // does nothing
        }

        fun thread() = this.thread
    }

    /**
     * Test that [Callback.OnMainThread] is called on main thread.
     */
    @Test
    fun `test that CallbackOnMaiThread is called on main thread`() {
        val threadedCallback = ThreadedCallback()
        Callback.OnMainThread(
            delegate = threadedCallback
        ).accept(42)
        threadedCallback.thread().shouldBe(
            Looper.getMainLooper().thread
        )
    }
}
package com.github.roroche.eoandroidconcurrency

import android.os.Looper
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import java.util.concurrent.CountDownLatch

/**
 * Tests of the [Callback] interface.
 */
class CallbackTest {

    private class ThreadedCallback(
        countDownLatch: CountDownLatch
    ) : Callback.WithCountDownLatch<Int>(countDownLatch) {
        private var data: Int = 0
        private lateinit var thread: Thread

        override fun accept(data: Int) {
            this.data = data
            this.thread = Thread.currentThread()
            super.accept(data)
        }

        override fun dispose() {
            throw NotImplementedError()
        }

        fun data() = this.data

        fun thread() = this.thread
    }

    /**
     * Test that [Callback.OnMainThread] is called on main thread.
     */
    @Test
    fun testThatCallbackOnMaiThreadCalledOnMainThread() {
        val countDownLatch = CountDownLatch(1)
        val threadedCallback = ThreadedCallback(countDownLatch)
        Callback.OnMainThread(
            delegate = threadedCallback
        ).accept(42)
        countDownLatch.await()
        assertThat(
            threadedCallback.data(),
            equalTo(
                42
            )
        )
        assertThat(
            threadedCallback.thread(),
            equalTo(
                Looper.getMainLooper().thread
            )
        )
    }
}

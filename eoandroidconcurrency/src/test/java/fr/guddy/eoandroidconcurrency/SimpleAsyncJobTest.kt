package fr.guddy.eoandroidconcurrency

import org.amshove.kluent.shouldBe
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Callable
import java.util.concurrent.CountDownLatch

/**
 * Class testing [SimpleAsyncJob], using [Callback.Fake] and [CountDownLatch].
 */
class SimpleAsyncJobTest {

    private lateinit var countDownLatch: CountDownLatch
    private lateinit var onSuccess: Callback.Fake<Boolean>
    private lateinit var onError: Callback.Fake<Throwable>

    @Before
    fun before() {
        countDownLatch = CountDownLatch(1)
        onSuccess = Callback.Fake(countDownLatch = countDownLatch)
        onError = Callback.Fake(countDownLatch = countDownLatch)
    }

    @Test
    fun `test on success callback is called`() {
        SimpleAsyncJob<Boolean>(
            callable = Callable {
                true
            },
            onSuccess = onSuccess,
            onError = onError
        ).run()
        countDownLatch.await()
        onSuccess.data!!.shouldBe(true)
    }

    @Test
    fun `test on error callback is called`() {
        SimpleAsyncJob<Boolean>(
            callable = Callable {
                throw RuntimeException("Error")
            },
            onSuccess = onSuccess,
            onError = onError
        ).run()
        countDownLatch.await()
        onError.data!!.message!!.shouldBe("Error")
    }
}
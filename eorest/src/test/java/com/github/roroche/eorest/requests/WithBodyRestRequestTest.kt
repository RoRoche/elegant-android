package com.github.roroche.eorest.requests

import com.github.roroche.eorest.requests.exceptions.NoBodyRestResponseException
import io.mockk.every
import io.mockk.mockk
import okhttp3.Response
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldThrowTheException
import org.amshove.kluent.withMessage
import org.junit.Before
import org.junit.Test

/**
 * Class testing [WithBodyRestRequest].
 */
class WithBodyRestRequestTest {

    private lateinit var response: Response
    private lateinit var restRequest: RestRequest

    /**
     * Called before each test.
     */
    @Before
    fun before() {
        response = mockk()
        restRequest = mockk()
        every {
            restRequest.response()
        } returns response
    }

    /**
     * Test [RestRequest] with body returns the [Response].
     */
    @Test
    fun testRequestWithBodyReturnsResponse() {
        every {
            response.body
        } returns mockk {
            every {
                string()
            } returns "true"
        }
        WithBodyRestRequest(
            origin = restRequest
        ).response().shouldBe(response)
    }

    /**
     * Test [RestRequest] without body throws a [NoBodyRestResponseException].
     */
    @Test
    fun testRequestWithoutBodyThrowsException() {
        every {
            response.body
        } returns null
        val func: () -> Unit = {
            WithBodyRestRequest(
                origin = restRequest
            ).response()
        }
        func shouldThrowTheException NoBodyRestResponseException::class withMessage "REST request does not have body"
    }
}

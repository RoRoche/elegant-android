package fr.guddy.eorest.requests

import fr.guddy.eorest.requests.exceptions.RestRequestFailureException
import io.mockk.every
import io.mockk.mockk
import okhttp3.Response
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldThrowTheException
import org.amshove.kluent.withMessage
import org.junit.Before
import org.junit.Test

/**
 * Class testing [SuccessfulRestRequest].
 */
class SuccessfulRestRequestTest {

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
     * Test successful [RestRequest] returns the [Response].
     */
    @Test
    fun `successful request returns response`() {
        every {
            response.isSuccessful
        } returns true
        SuccessfulRestRequest(
            origin = restRequest
        ).response().shouldBe(response)
    }

    /**
     * Test unsuccessful [RestRequest] throws a [RestRequestFailureException].
     */
    @Test
    fun `unsuccessful request throws exception`() {
        every {
            response.isSuccessful
        } returns false
        val func: () -> Unit = {
            SuccessfulRestRequest(
                origin = restRequest
            ).response()
        }
        func shouldThrowTheException RestRequestFailureException::class withMessage "REST request failed"
    }
}
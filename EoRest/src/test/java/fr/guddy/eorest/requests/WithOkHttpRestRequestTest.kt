package fr.guddy.eorest.requests

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.amshove.kluent.shouldBe
import org.junit.After
import org.junit.Test

/**
 * Tests for [RestRequest.WithOkHttp] class.
 */
class WithOkHttpRestRequestTest {

    var server: MockWebServer = MockWebServer()

    @After
    fun afterEach() {
        server.shutdown()
    }

    @Test
    fun `test that Call is used to fetch a response`() {
        server.enqueue(MockResponse().setBody("Hello World!"))
        server.start()
        val baseUrl: HttpUrl = server.url("/v1/greetings")
        RestRequest.WithOkHttp(
            client = OkHttpClient(),
            request = Request.Builder()
                .url(
                    baseUrl.newBuilder()
                        .addPathSegment("v1")
                        .addPathSegment("greetings")
                        .build()
                )
                .build()
        ).response().body!!.string().shouldBe("Hello World!")
        server.takeRequest().path!!.shouldBe("/v1/greetings")
    }
}
package infrastructure

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.javanet.NetHttpTransport
import com.maha.module
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.amshove.kluent.`should be equal to`
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test


internal class HttpClientSmokeTest {

    companion object {
        private lateinit var server: NettyApplicationEngine

        @BeforeClass
        @JvmStatic
        fun setup() {
            val env = applicationEngineEnvironment {
                module {
                    module(true)
                }
                connector {
                    host = "0.0.0.0"
                    port = 8080
                }
            }
            server = embeddedServer(Netty, env).start(false)
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            server.stop(1000, 10000)
        }
    }

    @Test
    fun `smoke test for the application`() {
        val requestFactory: HttpRequestFactory = NetHttpTransport().createRequestFactory()
        val request = requestFactory.buildGetRequest(
            GenericUrl("http://0.0.0.0:8080/health")
        )
        val response = request.execute()
        response.statusCode `should be equal to` 200
        response.parseAsString() `should be equal to` "OK"
    }


}
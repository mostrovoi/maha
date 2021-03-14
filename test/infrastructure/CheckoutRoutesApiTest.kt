package infrastructure

import com.maha.module
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.amshove.kluent.`should be equal to`
import org.junit.Test

internal class CheckoutRoutesApiTest {

    @Test
    fun `should return status 400 if wrong content type is set`() = withTestApplication(Application::module) {
        val call = handleRequest(HttpMethod.Post, "/checkout") {
            addHeader(HttpHeaders.ContentType, "wrpng content type")
            setBody(listOf("1", "2", "3").toString())
        }.apply {
            response.status()!!.value `should be equal to` 400
        }
    }

    @Test
    fun `should return status 400 if content type is ok but no data is set`() =
        withTestApplication(Application::module) {
            val call = handleRequest(HttpMethod.Post, "/checkout") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody("")
            }.apply {
                response.status()!!.value `should be equal to` 400
            }
        }

    @Test
    fun `should return status 200 if content type is ok but no data is set`() =
        withTestApplication(Application::module) {
            val call = handleRequest(HttpMethod.Post, "/checkout") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody("['1','2']")
            }.apply {
                response.status()!!.value `should be equal to` 200
                response.content `should be equal to` "{\"price\":180}"
            }
        }

    @Test
    fun `should return status 400 if content type is ok but some ids are not numbers`() =
        withTestApplication(Application::module) {
            val call = handleRequest(HttpMethod.Post, "/checkout") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody("['1','2','a']")
            }.apply {
                response.status()!!.value `should be equal to` 400
            }
        }

    @Test
    fun `should return status 400 if content type is ok but some ids are missing`() =
        withTestApplication(Application::module) {
            val call = handleRequest(HttpMethod.Post, "/checkout") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody("['1','2','400']")
            }.apply {
                response.status()!!.value `should be equal to` 400
            }
        }
}
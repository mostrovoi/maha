package com.maha

import com.maha.application.CheckoutUseCase
import com.maha.application.CheckoutUseCaseImpl
import com.maha.domain.WatchNotFoundException
import com.maha.domain.WatchRepository
import com.maha.infrastructure.CheckoutAdapter
import com.maha.infrastructure.InMemoryWatchRepository
import com.maha.infrastructure.registerCheckoutRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.serialization.*
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.ktor.kodein

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson()
        json()
    }
    install(DefaultHeaders)
    install(StatusPages) {
        exception<NumberFormatException> { cause ->
            log.error("Number format exception", cause)
            call.respond(HttpStatusCode.BadRequest)
        }
        exception<com.google.gson.JsonParseException> { cause ->
            log.error("JsonParseException", cause)
            call.respond(HttpStatusCode.BadRequest)
        }
        exception<BadRequestException> { cause ->
            log.error("BadRequestException", cause)
            call.respond(HttpStatusCode.BadRequest)
        }
        exception<WatchNotFoundException> { cause ->
            log.error("Watch not found exception")
            call.respond(HttpStatusCode.BadRequest)
        }
        exception<io.ktor.http.BadContentTypeFormatException> { cause ->
            log.error("Bad content type", cause)
            call.respond(HttpStatusCode.BadRequest)
        }
        exception<java.lang.ClassCastException> { cause ->
            log.error("Bad request sent.", cause)
            call.respond(HttpStatusCode.BadRequest)
        }
    }
    kodein {
        bind<WatchRepository>() with singleton { InMemoryWatchRepository() }
        bind<CheckoutUseCase>() with singleton { CheckoutUseCaseImpl(instance()) }
        bind<CheckoutAdapter>() with singleton { CheckoutAdapter(instance()) }
    }
    registerCheckoutRoutes()

}

package com.maha

import com.maha.infrastructure.DatabaseFactory
import com.maha.infrastructure.registerCheckoutRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }
    registerCheckoutRoutes()

    DatabaseFactory.init()
}

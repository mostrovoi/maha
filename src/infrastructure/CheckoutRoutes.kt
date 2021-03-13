package com.maha.infrastructure

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*

fun Route.checkoutRouting() {
    route("/checkout") {
        post {
            val listIds = call.receive<List<String>>()
        }
    }
}

fun Application.registerCheckoutRoutes() {
    routing {
        checkoutRouting()
    }
}
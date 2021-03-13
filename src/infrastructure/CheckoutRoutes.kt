package com.maha.infrastructure

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.generic.instance
import org.kodein.di.ktor.kodein


fun Route.checkoutRouting() {
    val checkoutAdapter by kodein().instance<CheckoutAdapter>()
    route("/checkout") {
        post {
            val listIds = call.receiveOrNull<List<String>>()
            if (listIds.isNullOrEmpty()) {
                throw BadRequestException("List of ids cannot be empty")
            }
            call.respond(checkoutAdapter.execute(listIds))
        }
    }
}

fun Application.registerCheckoutRoutes() {
    routing {
        checkoutRouting()
    }
}
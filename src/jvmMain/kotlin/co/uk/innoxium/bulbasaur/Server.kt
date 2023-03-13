package co.uk.innoxium.bulbasaur

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun HTML.index() {
    head {
        title("Bulbasaur Remote Client")
    }
    body {
        div {
            +"Hello from Ktor"
        }
        div {
            id = "root"
        }
        script(src = "/static/Bulbasaur.js") {}
    }
}

fun main() {
    embeddedServer(Netty, port = 5656, host = "127.0.0.1") {
        install(StatusPages) {
            status(HttpStatusCode.NotFound) {
                call, status ->
                call.respondRedirect("/")
            }
        }
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}
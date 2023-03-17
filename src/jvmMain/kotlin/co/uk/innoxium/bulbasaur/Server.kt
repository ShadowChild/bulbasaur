package co.uk.innoxium.bulbasaur

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun HTML.index() {
    head {
        title("Bulbasaur Remote Client")
        meta {
            httpEquiv = "X-Clacks-Overhead"
            content = "GNU Terry Pratchett"
        }
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/Bulbasaur.js") {}
    }
}

class Server {

    fun main() {
        embeddedServer(Netty, port = 5656, host = "127.0.0.1") {
            install(Authentication) {
                basic("basic-auth") {
                    realm = "access to application"
                    validate { credentials ->

                        if (credentials.name == "shad0w" && credentials.password == "testpass") {

                            UserIdPrincipal(credentials.name)
                        } else {
                            null
                        }
                    }
                }
            }
            install(StatusPages) {
                status(HttpStatusCode.NotFound) { call, _ ->
                    call.respondRedirect("/")
                }
            }
            install(ShutDownUrl.ApplicationCallPlugin) {
                shutDownUrl = "/pokeball"
                exitCodeSupplier = { 0 }
            }
            routing {
//                authenticate("basic-auth") {
                    get("/") {
                        call.respondHtml(HttpStatusCode.OK, HTML::index)
                    }
//                }
                static("/static") {
                    resources()
                }
            }
        }.start(wait = true)
    }
}
import react.create
import react.dom.client.createRoot
import web.dom.document

fun main() {
    val container = document.createElement("div")
    document.body.appendChild(container)


    val mainToolbar = MainToolbar.create {
        name = "Kotlin/JS"
    }
    createRoot(container).render(mainToolbar)
}
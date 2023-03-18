import react.create
import react.dom.client.createRoot
import web.dom.document

fun main() {
    val container = document.createElement("div")
    document.body.appendChild(container)

    val mainApp = MainApp.create {
        name = "Kotlin/JS"
    }
    createRoot(container).render(mainApp)
}
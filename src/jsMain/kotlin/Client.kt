import react.create
import react.dom.client.createRoot
import web.dom.document

fun main() {
    val container = document.createElement("div")
    document.body.appendChild(container)

    val mainApp = MainApp.create {

    }
    createRoot(container).render(mainApp)
}
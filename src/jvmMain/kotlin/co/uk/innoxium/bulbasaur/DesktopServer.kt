package co.uk.innoxium.bulbasaur

import me.friwi.jcefmaven.CefAppBuilder
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter
import org.cef.CefApp
import org.cef.browser.CefMessageRouter
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import kotlin.concurrent.thread
import kotlin.system.exitProcess


fun main(args: Array<String>) {

    if(args.isNotEmpty() && args[0].contentEquals("--daemon")) {

        beginServer()

    } else {

        thread(start = true, isDaemon = true) {

            beginServer()
        }
        // Create container for JCEF
        var frame = JFrame()
        // Create Progress Handler for JCEF
        val cefProgressHandler = CefProgressHandler(frame)
        cefProgressHandler.prepareFrame()

        // Init JCEF
        val builder = CefAppBuilder()
        builder.cefSettings.windowless_rendering_enabled = false
        builder.setProgressHandler(cefProgressHandler)

        builder.setAppHandler(object : MavenCefAppHandlerAdapter() {

            override fun stateHasChanged(state: CefApp.CefAppState?) {
                if(state == CefApp.CefAppState.TERMINATED) exitProcess(0)
            }
        })

        val cefApp = builder.build()
        val cefClient = cefApp.createClient()
        val cefMessageRouter = CefMessageRouter.create()
        cefClient.addMessageRouter(cefMessageRouter)
        val browser = cefClient.createBrowser("127.0.0.1:5656", false, false)

        frame = JFrame()

        frame.contentPane.add(browser.uiComponent)

        // Set size, location and set visible
        frame.title = "Bulbasaur Client "
        frame.pack()
        frame.setSize(1020, 768)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true

        frame.addWindowListener(object: WindowAdapter() {

            override fun windowClosing(e: WindowEvent?) {

                CefApp.getInstance().dispose()
                frame.dispose()
            }
        })
    }
}

fun beginServer() {

    Server().main()
}
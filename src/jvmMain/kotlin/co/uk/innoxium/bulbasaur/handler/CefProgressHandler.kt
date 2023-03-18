package co.uk.innoxium.bulbasaur.handler

import me.friwi.jcefmaven.EnumProgress
import me.friwi.jcefmaven.IProgressHandler
import java.awt.GridLayout
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JProgressBar
import kotlin.system.exitProcess

class CefProgressHandler(private val frame: JFrame) : IProgressHandler {

    private val progressBar = JProgressBar()
    fun prepareFrame() {

        frame.setSize(400, 400)
        frame.layout = GridLayout(2, 1)
        frame.title = "Bulbasaur Initial Setup"

        val label = JLabel("Setting up Bulbasaur")

        progressBar.minimum = 0
        progressBar.maximum = 100

        frame.add(label)
        frame.add(progressBar)
        frame.pack()
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.addWindowListener(object : WindowAdapter() {

            override fun windowClosing(e: WindowEvent?) {

                exitProcess(0)
            }
        })
        frame.isVisible = true
    }

    override fun handleProgress(state: EnumProgress?, percent: Float) {

        requireNotNull(state)

        progressBar.value = percent.toInt()

        if(state == EnumProgress.INITIALIZED) {

            frame.dispose()
        }
    }
}
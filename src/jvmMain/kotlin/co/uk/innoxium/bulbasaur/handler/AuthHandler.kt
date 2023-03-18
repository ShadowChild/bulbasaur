package co.uk.innoxium.bulbasaur.handler

import org.cef.browser.CefBrowser
import org.cef.callback.CefAuthCallback
import org.cef.handler.CefRequestHandlerAdapter

class AuthHandler : CefRequestHandlerAdapter() {

    override fun getAuthCredentials(
        browser: CefBrowser?,
        origin_url: String?,
        isProxy: Boolean,
        host: String?,
        port: Int,
        realm: String?,
        scheme: String?,
        callback: CefAuthCallback?
    ): Boolean {

        println("scheme = $scheme")

        callback?.Continue("shad0w", "testpass")
        return super.getAuthCredentials(browser, origin_url, isProxy, host, port, realm, scheme, callback)
    }
}

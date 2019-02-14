package com.minosai.threadtest

import java.net.HttpURLConnection
import java.net.URL

class ChildThread(
    private val number: String,
    private val url: String,
    private val listener: NetworkCallback
) : Runnable {

    override fun run() {

        var result = ""
        var code = -1

        try {

            val siteURL = URL(url)
            val connection = siteURL.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 3000
            connection.connect()

            code = connection.responseCode
            result = "Number: $number Code: $code"

        } catch (e: Exception) {
            result = "Number $number Exception: " + e.message
        }

        listener.onDataReceive(result)
    }
}

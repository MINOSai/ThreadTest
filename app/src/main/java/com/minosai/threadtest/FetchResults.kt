package com.minosai.threadtest

import android.os.AsyncTask
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FetchResults(private var activity: MainActivity) : AsyncTask<String, Unit, String>() {

    override fun doInBackground(vararg urlList: String): String {

        val responseList = arrayListOf<String>()

        val executorService = Executors.newFixedThreadPool(urlList.size)

        for (i in 0 until urlList.size) {
            val childThread = ChildThread(i.toString(), urlList[i], object : NetworkCallback {

                override fun onDataReceive(response: String) {
                    responseList.add(response)
                }

            })
            executorService.execute(childThread)
        }
        executorService.shutdown()

        while (!executorService.isTerminated) {

        }

        return responseList.joinToString()
    }

    override fun onPostExecute(result: String) {
        Log.d("API RESPONSE", result)
        activity.text_main.text = result
    }

}
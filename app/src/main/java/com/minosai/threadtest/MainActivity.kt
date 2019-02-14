package com.minosai.threadtest

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val urlList = listOf(
            "https://jsonplaceholder.typicode.com/posts",
            "https://jsonplaceholder.typicode.com/comments",
            "https://jsonplaceholder.typicode.com/todos"
        ).toTypedArray()

        FetchResults(this).execute(*urlList)
    }
}

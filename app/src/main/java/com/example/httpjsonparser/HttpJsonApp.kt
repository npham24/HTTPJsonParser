package com.example.httpjsonparser

import android.app.Application
import com.example.httpjsonparser.Manager.ApiManager
import kotlin.random.Random

class HttpJsonApp: Application() {
    lateinit var apiManager: ApiManager
        private set
    var viewCount: Int = Random.nextInt(100000)
    var username = "npham"

    override fun onCreate() {
        super.onCreate()

        // Load managers
        apiManager = ApiManager(this)

    }
}
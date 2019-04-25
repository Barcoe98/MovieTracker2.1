package org.wit.movietracker.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.movietracker.models.WatchItemMemStore

class MainApp: Application(), AnkoLogger {

    val watchItems = WatchItemMemStore()

    override fun onCreate() {
        super.onCreate()
        info("WatchList Started")
    }
}
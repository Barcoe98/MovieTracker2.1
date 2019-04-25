package org.wit.movietracker.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.movietracker.models.WatchItemJSONStore
import org.wit.movietracker.models.WatchItemStore

class MainApp: Application(), AnkoLogger {


    lateinit var watchitems: WatchItemStore

    override fun onCreate() {
        super.onCreate()
        watchitems = WatchItemJSONStore(applicationContext)

        info("WatchList Started")
    }
}
package org.wit.movietracker.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.movietracker.models.WatchItemJSONStore
import org.wit.movietracker.models.WatchItemStore
import org.wit.movietracker.models.WatchedMovieJSONStore
import org.wit.movietracker.models.WatchedMovieStore

class MainApp: Application(), AnkoLogger {


    lateinit var watchitems: WatchItemStore
    lateinit var watchedMovies: WatchedMovieStore

    override fun onCreate() {
        super.onCreate()
        watchitems = WatchItemJSONStore(applicationContext )
        watchedMovies = WatchedMovieJSONStore(applicationContext)

        info("WatchList Started")
    }
}
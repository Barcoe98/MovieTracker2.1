package org.wit.movietracker.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.movietracker.helpers.*
import java.util.*

val JSON_FILE2 = "watchedMovies.json"
val gsonBuilder2 = GsonBuilder().setPrettyPrinting().create()
val listType2 = object : TypeToken<java.util.ArrayList<WatchedMovieModel>>() {}.type

fun generateRandomId2(): Long {
    return Random().nextLong()
}

class WatchedMovieJSONStore : WatchedMovieStore, AnkoLogger {

    val context: Context
    var watchedMovies = mutableListOf<WatchedMovieModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE2)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<WatchedMovieModel> {
        return watchedMovies
    }

    override fun create(watchedMovie: WatchedMovieModel) {
        watchedMovie.watchedId = generateRandomId()
        watchedMovies.add(watchedMovie)
        serialize()
    }

    override fun update(watchedMovie: WatchedMovieModel) {
        var foundWatchedMovie: WatchedMovieModel? = watchedMovies.find { p -> p.watchedId == watchedMovie.watchedId }
        if (foundWatchedMovie != null) {
            foundWatchedMovie.watchedTitle = watchedMovie.watchedTitle
            foundWatchedMovie.watchedComment = watchedMovie.watchedComment
            foundWatchedMovie.watchedRating = watchedMovie.watchedRating
        }
        serialize()
    }

    override fun remove(watchedMovie: WatchedMovieModel) {
        watchedMovies.remove(watchedMovie)
        serialize()
    }

    override fun length(watchedMovie: WatchedMovieModel){
       watchedMovies.size

    }

    private fun serialize() {
        val jsonString2 = gsonBuilder2.toJson(watchedMovies, listType2)
        write(context, JSON_FILE2, jsonString2)
    }

    private fun deserialize() {
        val jsonString2 = read(context, JSON_FILE2)
        watchedMovies = Gson().fromJson(jsonString2, listType2)
    }
}
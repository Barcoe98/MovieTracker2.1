package org.wit.movietracker.models


interface WatchedMovieStore {
    fun findAll(): List<WatchedMovieModel>
    fun create(watchedItem: WatchedMovieModel)
    fun update(watchedItem: WatchedMovieModel)
    fun remove(watchedItem: WatchedMovieModel)
}

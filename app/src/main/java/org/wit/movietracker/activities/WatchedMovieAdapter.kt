package org.wit.movietracker.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_watchedmovie.view.*
import kotlinx.android.synthetic.main.card_watchitem.view.*
import org.wit.movietracker.R
import org.wit.movietracker.models.WatchItemModel
import org.wit.movietracker.models.WatchedMovieModel

interface WatchedMovieListener {
    fun onWatchedMovieClick(watchedMovie: WatchedMovieModel)
}

class WatchedMovieAdapter constructor(private var watchedMovies: List<WatchedMovieModel>,
                                      private val listener: WatchedMovieListener)
    : RecyclerView.Adapter<WatchedMovieAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_watchedmovie, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val watchedMovie = watchedMovies[holder.adapterPosition]
        holder.bind(watchedMovie, listener)
    }

    override fun getItemCount(): Int = watchedMovies.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(watchedMovie: WatchedMovieModel,  listener : WatchedMovieListener) {
          itemView.watchedMovieTitleList.text = watchedMovie.watchedTitle
            itemView.watchedMovieCommentList.text = watchedMovie.watchedComment
           itemView.watchedMovieRatingList.text = watchedMovie.watchedComment
            itemView.setOnClickListener { listener.onWatchedMovieClick(watchedMovie) }
        }
    }
}

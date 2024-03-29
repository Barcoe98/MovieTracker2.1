package org.wit.movietracker.activities

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_movie.view.*
import org.wit.movietracker.R
import org.wit.movietracker.models.MovieModel


interface MovieListener {
    fun onMovieClick(movie: MovieModel)
}

class MovieAdapter (var context: Context,
                    var movies: List<MovieModel>) :
    RecyclerView.Adapter<MovieAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val movie = movies[holder.adapterPosition]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //binds text from model to the card view
        fun bind(movies: MovieModel) {
            itemView.movieTitle.text = movies.originalTitle
            itemView.movieDate.text = movies.releaseDate
            //itemView.setOnClickListener { listener.onMovieClick(movies) }
        }
    }



}
/*
activity for movie_list.xml
handle events for recycler view, like click on watchedMovie
loads watchedMovies
 */

package org.wit.movietracker.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.watchedmovie_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.movietracker.R
import org.wit.movietracker.main.MainApp
import org.wit.movietracker.models.WatchedMovieModel

class WatchedMovieListActivity : AppCompatActivity(), WatchedMovieListener{

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.watchedmovie_list)
        app = application as MainApp

        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerViewWatched.layoutManager = layoutManager

        //loads movies to recycler view when created
        loadWatchedMovies()

        //enable action bar and set title
        toolbarWatchedList.title = title
        setSupportActionBar(toolbarWatchedList)

        //watchedMoviesCount.setText(Counter.counter)
    }

    //loads watched movies from array
    private fun loadWatchedMovies() {
        showWatchedMovies(app.watchedMovies.findAll())
    }

    //
    private fun showWatchedMovies (watchedMovies: List<WatchedMovieModel>) {
        recyclerViewWatched.adapter = WatchedMovieAdapter(watchedMovies, this)
        recyclerViewWatched.adapter?.notifyDataSetChanged()
    }

    //populates view with selected menu (menu_mainwatchedmovie)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mainwatchedmovie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //method to handle icon clicks on the menu bar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            //when user clicks icon WatchedmovieActivity
            R.id.item_addWatchedMovie -> {
                startActivityForResult<WatchedMovieActivity>(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //when a watchedMovie is clicked, it will start a new activity, so user can edit the watched Movie
    override fun onWatchedMovieClick(watchedMovie: WatchedMovieModel) {
        startActivityForResult(intentFor<WatchedMovieActivity>().putExtra("watchedMovie_edit", watchedMovie), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //loads the watched Movies from a file
        loadWatchedMovies()
        super.onActivityResult(requestCode, resultCode, data)
    }

}
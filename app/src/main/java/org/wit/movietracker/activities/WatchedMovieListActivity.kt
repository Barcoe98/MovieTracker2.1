package org.wit.movietracker.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.watchedmovie_list.*
import kotlinx.android.synthetic.main.watchitem_list.*
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
        //recyclerView.adapter = WatchItemAdapter(app.watchItems.findAll(), this)
        loadWatchedMovies()

        //enable action bar and set title
        toolbarWatchedList.title = title
        setSupportActionBar(toolbarWatchedList)
    }

    private fun loadWatchedMovies() {
        showWatchedMovies(app.watchedMovies.findAll())
    }

    fun showWatchedMovies (watchedMovies: List<WatchedMovieModel>) {
        recyclerViewWatched.adapter = WatchedMovieAdapter(watchedMovies, this)
        recyclerViewWatched.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mainwatchedmovie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_watchList -> {
                startActivityForResult<WatchItemListActivity>(0)
            }
            R.id.item_addWatchedMovie -> {
                startActivityForResult<WatchedMovieActivity>(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onWatchedMovieClick(watchedMovie: WatchedMovieModel) {
        startActivityForResult(intentFor<WatchedMovieActivity>().putExtra("watchedMovie_edit", watchedMovie), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //lets recyclerView that there has been a change and updates the view
        //recyclerView.adapter?.notifyDataSetChanged()
        loadWatchedMovies()
        super.onActivityResult(requestCode, resultCode, data)
    }

}
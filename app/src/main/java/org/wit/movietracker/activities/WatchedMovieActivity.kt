package org.wit.movietracker.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_statistic.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.movietracker.R
import org.wit.movietracker.main.MainApp
import kotlinx.android.synthetic.main.new_watchedmovie.*
import org.wit.movietracker.models.WatchedMovieModel


class WatchedMovieActivity : AppCompatActivity(), AnkoLogger {

    var watchedMovie = WatchedMovieModel()
    lateinit var app: MainApp
    var edit = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_watchedmovie)

        app = application as MainApp

        if (intent.hasExtra("watchedMovie_edit"))
        {
            edit = true

            watchedMovie = intent.extras.getParcelable<WatchedMovieModel>("watchedMovie_edit")

              watchedMovieTitle.setText(watchedMovie.watchedTitle)
              watchedMovieComment.setText(watchedMovie.watchedComment)
              watchedMovieRating.setText(watchedMovie.watchedRating)

            btnAddWatchedMovie.setText(R.string.save_watchedmovie)
        }

        btnAddWatchedMovie.setOnClickListener {

            watchedMovie.watchedTitle = watchedMovieTitle.text.toString()
            watchedMovie.watchedComment = watchedMovieComment.text.toString()
            watchedMovie.watchedRating = watchedMovieRating.text.toString()

            if (watchedMovie.watchedTitle.isEmpty()) {
                toast(R.string.enter_watchedmovie_title)
            }
            else if (watchedMovie.watchedComment.isEmpty()) {
                toast(R.string.enter_watchedmovie_comment)
            }
            else if (watchedMovie.watchedRating.isEmpty()) {
                toast(R.string.enter_watchedmovie_rating)
            }
            else {
                if(edit){
                    app.watchedMovies.update(watchedMovie.copy())
                }
                else{
                    app.watchedMovies.create(watchedMovie.copy())
                   // Counter.counter + 1
                }
                info("Add Button Pressed. name: ${watchedMovie.watchedTitle}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
        }
        //Add action bar and set title
        toolbarWatched.title = title
        setSupportActionBar(toolbarWatched)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_watchedmovie, menu)
        if (edit && menu != null) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            //when icon is clicked deleted selected Watched Movie
            R.id.item_deleteWatched -> {
                app.watchedMovies.remove(watchedMovie)
                finish()
            }
            //when icon is clicked finishes activity and returns to previous screen
            R.id.item_cancelWatched -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

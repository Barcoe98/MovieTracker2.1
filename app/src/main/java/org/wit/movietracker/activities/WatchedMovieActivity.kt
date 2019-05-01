package org.wit.movietracker.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.new_watchitem.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.movietracker.R
import org.wit.movietracker.main.MainApp
import org.wit.movietracker.models.WatchItemModel
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import kotlinx.android.synthetic.main.new_watchedmovie.*
import org.wit.movietracker.models.WatchedMovieModel


class WatchedMovieActivity : AppCompatActivity(), AnkoLogger {

    var watchedMovie = WatchedMovieModel()
    var watchItem = WatchItemModel()

    lateinit var app: MainApp
    var edit = false
    var watched = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_watchedmovie)
        app = application as MainApp

        if (intent.hasExtra("watchedMovie_edit"))
        {
            edit = true

            //watchedMovie = intent.extras.getParcelable<WatchedMovieModel>("watchedMovie_edit")
            watchItem = intent.extras.getParcelable<WatchItemModel>("watchItem_edit")

             // watchedMovieTitle.setText(watchedMovie.watchedTitle)
              watchedMovieComment.setText(watchedMovie.watchedComment)
              watchedMovieRating.setText(watchedMovie.watchedRating)

            watchedMovieTitle.setText(watchItem.title)
            //watchedMovieTitle.text(watchItem.title)

            //watchedMovie = intent.extras.getParcelable<WatchedMovieModel>("watchedMovie_edit")
            //watchItem = intent.extras.getParcelable<WatchItemModel>("watchItem_edit")

            // watchItem.title = watchedMovieTitle.text.toString()

            //val Title : TextView = findViewById(R.id.watchitemTitle) as TextView
           // val str: String = watchitemTitle.text.toString()

            //watchedMovie.watchedComment = watchedMovieComment.text.toString()
            //watchedMovieTitle.setText(watchItem.title)
           // watchedMovieTitle.setText("green")
            //watchitemTime.setText(watchItem.time)

           // watchedMovieRating.setText(watchItem.time)

            btn_addWatchedMovie.setText(R.string.save_watchedMovie)

        }


        btn_addWatchedMovie.setOnClickListener {

            watchedMovie.watchedTitle = watchedMovieTitle.text.toString()
            watchedMovie.watchedComment = watchedMovieComment.text.toString()
            watchedMovie.watchedRating = watchedMovieRating.text.toString()

            if (watchedMovie.watchedTitle.isEmpty()) {
                toast(R.string.enter_watchitem_title)
            }
            else if (watchedMovie.watchedComment.isEmpty()) {
                toast(R.string.enter_watchitem_desc)
            }
            else if (watchedMovie.watchedRating.isEmpty()) {
                toast(R.string.enter_watchitem_time)
            }
            else {
                if(edit){
                    app.watchedMovies.update(watchedMovie.copy())
                }
                else{
                    app.watchedMovies.create(watchedMovie.copy())
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
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_deleteWatched -> {
                app.watchedMovies.remove(watchedMovie)
                finish()
            }
            R.id.item_cancelWatched -> {
                finish()
            }
            R.id.item_addWatched -> {
                startActivityForResult<WatchedMovieActivity>(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

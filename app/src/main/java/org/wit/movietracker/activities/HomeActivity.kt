package org.wit.movietracker.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivityForResult
import org.wit.movietracker.R
import kotlinx.android.synthetic.main.home_screen.*

class HomeActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        btnMovies.setOnClickListener{
            startActivityForResult<WatchedMovieActivity>(0)
        }

        btnWatchList.setOnClickListener{
            startActivityForResult<WatchItemListActivity>(0)
        }

        btnWatchedMovie.setOnClickListener{
            startActivityForResult<WatchedMovieListActivity>(0)
        }
    }

}

/*
activity for home_screen.xml
Short paragraph where it describes what the app does.
contains 4 buttons to navigate through the app.
 */

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

        //watchedMoviesCount.setText(Counter.counter)

        //when button clicked starts activity MovieListActivity
        btnMovies.setOnClickListener{
            startActivityForResult<MovieListActivity>(0)
        }

        //when button clicked starts activity WatchItemListActivity
        btnWatchList.setOnClickListener{
            startActivityForResult<WatchItemListActivity>(0)
        }

        //when button clicked starts activity WatchedMovieListActivity
        btnWatchedMovie.setOnClickListener{
            startActivityForResult<WatchedMovieListActivity>(0)
        }

        //when button clicked starts activity StatisticActivity
        btnStatistics.setOnClickListener{
            startActivityForResult<StatisticActivity>(0)
        }
    }

}

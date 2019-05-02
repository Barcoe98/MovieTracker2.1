package org.wit.movietracker.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.movie_list.*
import org.jetbrains.anko.toast
import org.wit.movietracker.R
import org.wit.movietracker.api.ApiClient
import org.wit.movietracker.api.ApiInterface
import org.wit.movietracker.main.MainApp
import org.wit.movietracker.models.MovieModel
import org.wit.movietracker.models.MovieResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListActivity  : AppCompatActivity() {

    lateinit var app: MainApp

    val TAG: String = MainApp::class.java.simpleName
    val API_KEY: String = "0306b1ac6480f6ec8ea92d46a2d7d2f7"
    lateinit var myMovieAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.movie_list)

        val layoutManager = LinearLayoutManager(this)
        recyclerViewMovie.layoutManager = layoutManager

        //enable action bar and set title
        //toolbarMovie.title = title
          setSupportActionBar(toolbarMovie)


        if (API_KEY.isEmpty()) {
            toast("Please obtain your API KEY first from www.themoviedb.org")
            return
        }

        var apiServices = ApiClient.client.create(ApiInterface::class.java)

        val call = apiServices.getMovies(API_KEY)

        call.enqueue(object : Callback<MovieResults> {
            override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {

                var listOfMovies: List<MovieModel> = response.body()?.results!!
                myMovieAdapter = MovieAdapter(applicationContext, listOfMovies)
                recyclerViewMovie.adapter = myMovieAdapter

            }
            override fun onFailure(call: Call<MovieResults>?, t: Throwable?) {
                toast("Please obtain your API KEY first from www.themoviedb.org")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancelMovie -> {
            finish()
          }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        //recyclerView is a widget in activity_placemark_list.xml
        recyclerViewMovie.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
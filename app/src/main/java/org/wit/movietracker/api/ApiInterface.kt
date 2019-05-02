package org.wit.movietracker.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import android.graphics.Movie
import org.wit.movietracker.models.MovieResults
import retrofit2.http.Path


interface ApiInterface {

    @GET("/3/movie/popular/")
    fun getMovies(

        @Query("api_key") apiKey: String
    ): Call<MovieResults>

}
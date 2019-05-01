package org.wit.movietracker.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

    @Parcelize
    data class WatchedMovieModel (var watchedId: Long =0,
                                  var watchedTitle: String = "",
                                  var watchedComment: String = "",
                                  var watchedRating: String = "") : Parcelable {
    }

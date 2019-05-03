package org.wit.movietracker.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WatchItemModel (var id: Long =0,
                           var title: String = "",
                           var length: String = "",
                           var time: String = "") : Parcelable {
}
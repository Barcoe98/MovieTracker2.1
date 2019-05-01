package org.wit.movietracker.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WatchItemModel (var id: Long =0,
                           var title: String = "",
                           var description: String = "",
                           var time: String = "") : Parcelable {
}
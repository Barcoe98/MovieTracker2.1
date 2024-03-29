package org.wit.movietracker.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.movietracker.helpers.*
import java.util.*

val JSON_FILE = "watchitems.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<WatchItemModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class WatchItemJSONStore : WatchItemStore, AnkoLogger {

    val context: Context
    var watchitems = mutableListOf<WatchItemModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<WatchItemModel> {
        return watchitems
    }

    //creates a watchitem when used, said watch item is given an id and then
    override fun create(watchitem: WatchItemModel) {
        watchitem.id = generateRandomId()
        watchitems.add(watchitem)
        serialize()
    }

    //updates watchItem
    override fun update(watchitem: WatchItemModel) {
        var foundWatchitem: WatchItemModel? = watchitems.find { p -> p.id == watchitem.id }
        if (foundWatchitem != null) {
            foundWatchitem.title = watchitem.title
            foundWatchitem.length = watchitem.length
            foundWatchitem.time = watchitem.time
        }
        serialize()
    }

    override fun remove(watchitem: WatchItemModel) {
        watchitems.remove(watchitem)
        serialize()

    }

    override fun length(watchitem: WatchItemModel){
        watchitems.size

    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(watchitems, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        watchitems = Gson().fromJson(jsonString, listType)
    }

}
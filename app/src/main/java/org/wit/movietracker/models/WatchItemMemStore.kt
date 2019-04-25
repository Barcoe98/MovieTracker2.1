package org.wit.movietracker.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class WatchItemMemStore : WatchItemStore, AnkoLogger {

    val watchItems = ArrayList<WatchItemModel>()

    override fun findAll(): List<WatchItemModel> {
        return watchItems
    }

    override fun create(watchitem: WatchItemModel) {
        watchitem.id = getId()
        watchItems.add(watchitem)
        logAll()
    }

    override fun remove(watchitem: WatchItemModel) {
        watchItems.remove(watchitem)
        logAll()
    }

    override fun update(watchitem: WatchItemModel) {
        var foundWatchitem: WatchItemModel? = watchItems.find { p -> p.id == watchitem.id }
        if (foundWatchitem != null) {
            foundWatchitem.title = watchitem.title
            foundWatchitem.description = watchitem.description
            logAll()
        }
    }

    fun logAll() {
        watchItems.forEach { info("${it}") }
    }
}

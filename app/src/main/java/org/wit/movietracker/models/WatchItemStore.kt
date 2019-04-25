package org.wit.movietracker.models


interface WatchItemStore {
    fun findAll(): List<WatchItemModel>
    fun create(watchItem: WatchItemModel)
    fun update(watchItem: WatchItemModel)
    fun remove(watchItem: WatchItemModel)
}

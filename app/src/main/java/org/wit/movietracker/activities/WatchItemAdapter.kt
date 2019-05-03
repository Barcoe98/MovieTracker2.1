package org.wit.movietracker.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_watchitem.view.*
import org.wit.movietracker.R
import org.wit.movietracker.models.WatchItemModel

interface WatchItemListener {
    fun onWatchItemClick(watchItem: WatchItemModel)
}

class WatchItemAdapter constructor(private var watchItems: List<WatchItemModel>,
                                   private val listener: WatchItemListener)
    : RecyclerView.Adapter<WatchItemAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_watchitem, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val watchItem = watchItems[holder.adapterPosition]
        holder.bind(watchItem, listener)
    }

    override fun getItemCount(): Int = watchItems.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(watchItem: WatchItemModel,  listener : WatchItemListener) {
            itemView.watchitemTitleList.text= watchItem.title
            itemView.watchitemLengthList.text = watchItem.length
            itemView.watchitemTimeList.text = watchItem.time
            itemView.setOnClickListener { listener.onWatchItemClick(watchItem) }
        }
    }
}

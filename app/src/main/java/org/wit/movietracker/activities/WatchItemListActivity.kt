/*
activity for watchitem_list.xml

 */

package org.wit.movietracker.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.watchitem_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.movietracker.R
import org.wit.movietracker.main.MainApp
import org.wit.movietracker.models.WatchItemModel

class WatchItemListActivity : AppCompatActivity(), WatchItemListener{

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.watchitem_list)
        app = application as MainApp

        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //populates display with watchItems
        loadWatchItems()

        //enable action bar and set title
        toolbarWatchItem.title = title
        setSupportActionBar(toolbarWatchItem)
    }

    // grabs all watchItems and populates to recyclerview
    private fun loadWatchItems() {
        showWatchItems(app.watchitems.findAll())
    }

    // populates recycler view with watchitem Model
    private fun showWatchItems (watchitems: List<WatchItemModel>) {
        recyclerView.adapter = WatchItemAdapter(watchitems, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    //loads screen with menu_main
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //handles clicks on menu_main
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            //when icon clicked WatchItemActvity is started
            R.id.item_addWatchItem -> {
                startActivityForResult<WatchItemActivity>(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //when watchItem is clicked will load up WatchItemActivity and allowuser to edit selected watchItem
    override fun onWatchItemClick(watchItem: WatchItemModel) {
        startActivityForResult(intentFor<WatchItemActivity>().putExtra("watchItem_edit", watchItem), 0)
    }

    //reloads page when item added/deleted or edited
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadWatchItems()
        super.onActivityResult(requestCode, resultCode, data)
    }

}
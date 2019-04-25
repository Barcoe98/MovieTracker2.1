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
        recyclerView.adapter = WatchItemAdapter(app.watchItems.findAll(), this)

        //enable action bar and set title
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<WatchItemActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onWatchItemClick(watchItem: WatchItemModel) {
        startActivityForResult(intentFor<WatchItemActivity>().putExtra("watchItem_edit", watchItem), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_placemark_list.xml
        //lets recyclerView that there has been a change and updates the view
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
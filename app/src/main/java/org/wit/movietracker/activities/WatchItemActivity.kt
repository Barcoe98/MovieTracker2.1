/*
activity for new_watchitem
 */

package org.wit.movietracker.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.new_watchitem.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.movietracker.R
import org.wit.movietracker.main.MainApp
import org.wit.movietracker.models.WatchItemModel

class WatchItemActivity : AppCompatActivity(), AnkoLogger {

    var watchItem = WatchItemModel()
    lateinit var app: MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_watchitem)
        app = application as MainApp
        btnAddToWatchedMovie.visibility= View.GONE

        if (intent.hasExtra("watchItem_edit"))
        {
            edit = true

            watchItem = intent.extras.getParcelable<WatchItemModel>("watchItem_edit")
            watchitemTitle.setText(watchItem.title)
            watchitemLength.setText(watchItem.length)
            watchitemTime.setText(watchItem.time)
            btnAddWatchItem.setText(R.string.save_watchitem)
            btnAddToWatchedMovie.visibility= View.VISIBLE
        }


        btnAddWatchItem.setOnClickListener {

            watchItem.title = watchitemTitle.text.toString()
            watchItem.length = watchitemLength.text.toString()
            watchItem.time = watchitemTime.text.toString()

            if (watchItem.title.isEmpty()) {
                toast(R.string.enter_watchitem_title)
            }
            else if (watchItem.length.isEmpty()) {
                toast(R.string.enter_watchitem_length)
            }
            else if (watchItem.time.isEmpty()) {
                toast(R.string.enter_watchitem_time)
            }
            else {
                if(edit){
                    app.watchitems.update(watchItem.copy())
                }
                else{
                    app.watchitems.create(watchItem.copy())
                }
                info("Add Button Pressed. name: ${watchItem.title}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
        }

        btnAddToWatchedMovie.setOnClickListener{
            startActivityForResult<WatchedMovieActivity>(0)
            deleteCurrentWatchItem()
           // fillDetailsIn()
        }
        //Add action bar and set title
        //toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_watchitem, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
        R.id.item_deleteWatchItem -> {
            app.watchitems.remove(watchItem)
            finish()
        }
        R.id.item_cancelWatchItem -> {
            finish()
        }
    }
    return super.onOptionsItemSelected(item)
}

    fun deleteCurrentWatchItem(){
        app.watchitems.remove(watchItem)
    }

    fun fillDetailsIn(){
        //var mTitle = findViewById(R.id.watchedMovieTitle)
        //mTitle.setText(watchItem.title)

    }

}

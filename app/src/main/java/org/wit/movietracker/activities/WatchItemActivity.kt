package org.wit.movietracker.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.new_watchitem.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.movietracker.R
import org.wit.movietracker.main.MainApp
import org.wit.movietracker.models.WatchItemModel
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.TextView
import kotlinx.android.synthetic.main.new_watchedmovie.*
import org.wit.movietracker.models.WatchedMovieModel


class WatchItemActivity : AppCompatActivity(), AnkoLogger {

    var watchItem = WatchItemModel()
    var watchedMovie = WatchedMovieModel()

    lateinit var app: MainApp
    var edit = false
   // lateinit var mDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_watchitem)
        app = application as MainApp
        //btnRemove.visibility = View.INVISIBLE
       // item_delete.visibility = View.INVISIBLE

       // mDatabase = FirebaseDatabase.getInstance().reference

        if (intent.hasExtra("watchItem_edit"))
        {
            edit = true
            //set the remove button visible when edit watch item
           // btnRemove.visibility = View.VISIBLE

            watchItem = intent.extras.getParcelable<WatchItemModel>("watchItem_edit")
            watchitemTitle.setText(watchItem.title)
            watchitemDesc.setText(watchItem.description)
            watchitemTime.setText(watchItem.time)
            btnAddWatchItem.setText(R.string.save_watchitem)

        }

        btnAddWatchedMovie.setOnClickListener{

            startActivityForResult<WatchedMovieActivity>(0)
            app.watchitems.remove(watchItem)
        }

        btnAddWatchItem.setOnClickListener {

            watchItem.title = watchitemTitle.text.toString()
            watchItem.description = watchitemDesc.text.toString()
            watchItem.time = watchitemTime.text.toString()

            if (watchItem.title.isEmpty()) {
                toast(R.string.enter_watchitem_title)
            }
            else if (watchItem.description.isEmpty()) {
                toast(R.string.enter_watchitem_desc)
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

}

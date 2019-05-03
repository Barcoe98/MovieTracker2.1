package org.wit.movietracker.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.wit.movietracker.R
import org.wit.movietracker.main.MainApp

class StatisticActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_statistic)

        //movieCount.setText(Counter.counter)

        }




}


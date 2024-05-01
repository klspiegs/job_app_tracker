package com.example.job_app_tracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.ListView

class CalendarActivity: AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        listView = findViewById(R.id.jobs)
        var jobs: ArrayList<String> = ArrayList()

        var calendarView: CalendarView = findViewById(R.id.calendar)
        var calListener: CalListener = CalListener()
        calendarView.setOnDateChangeListener(calListener)

        displayList(jobs)
    }

    fun displayList(jobs: ArrayList<String>) {
        var adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, jobs)
        listView.adapter = adapter
    }

    inner class CalListener: CalendarView.OnDateChangeListener {
        override fun onSelectedDayChange(
            view: CalendarView,
            year: Int,
            month: Int,
            dayOfMonth: Int
        ) {
            //Will show only jobs on that day
        }
    }
}
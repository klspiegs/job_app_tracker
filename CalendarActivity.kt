package com.example.job_app_tracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.ListView
import android.widget.TextView

class CalendarActivity: AppCompatActivity() {

    private lateinit var listView : ListView
    private lateinit var textView: TextView
    private lateinit var allJobs: ArrayList<Job>
    private lateinit var clickedJobs: ArrayList<Job>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        listView = findViewById(R.id.jobs)
        textView = findViewById(R.id.jobText)

        var calendarView: CalendarView = findViewById(R.id.calendar)
        var calListener: CalListener = CalListener()
        calendarView.setOnDateChangeListener(calListener)

        var homeButton: Button = findViewById(R.id.home)
        homeButton.setOnClickListener{finish()}

        var allButton: Button = findViewById(R.id.all)
        allButton.setOnClickListener{showAllJobs()}

        //Testing stuff, will just pull jobs from firebase
        allJobs = ArrayList()
        var testJob1: Job = Job()
        testJob1.setJobName("Engineer")
        testJob1.setCompanyName("Google")
        testJob1.setDeadline(Triple(4,5,2024))
        allJobs.add(testJob1)

        var testJob2: Job = Job()
        testJob2.setJobName("IT Technician")
        testJob2.setCompanyName("Amazon")
        testJob2.setDeadline(Triple(12,5,2024))
        allJobs.add(testJob2)

        showAllJobs()

    }

    fun toJobPage(index: Int) {
        currJob = clickedJobs[index]
        var myIntent: Intent = Intent(this, JobActivity::class.java)
        startActivity(myIntent)
    }

    fun displayList(jobs: ArrayList<Job>) {
        var jobTitles: ArrayList<String> = ArrayList()
        for(job in jobs){
            jobTitles.add(job.getCompanyName() + " -- " + job.getJobName())
        }
        var adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, jobTitles)
        listView.adapter = adapter

        var lih: ListItemHandler = ListItemHandler()
        listView.setOnItemClickListener(lih)
    }

    fun showAllJobs() {
        textView.text = "Showing All Jobs"
        clickedJobs = allJobs
        displayList(allJobs)
    }

    inner class ListItemHandler: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            toJobPage(position)
        }
    }

    inner class CalListener: CalendarView.OnDateChangeListener {
        override fun onSelectedDayChange(
            view: CalendarView,
            year: Int,
            month: Int,
            dayOfMonth: Int
        ) {
            var jobsOnDay: ArrayList<Job> = ArrayList()
            for(job in allJobs){
                if(job.getDeadline().first == dayOfMonth && job.getDeadline().second == (month+1) && job.getDeadline().third == year){
                    jobsOnDay.add(job)
                }
            }
            clickedJobs = jobsOnDay
            displayList(jobsOnDay)
            textView.text = "Showing Jobs Due On " + (month+1).toString() + "/" + dayOfMonth.toString() + "/" + year.toString()
        }
    }

    companion object {
        lateinit var currJob: Job
    }
}
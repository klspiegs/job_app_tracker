package com.example.job_app_tracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class JobActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

        var backButton: Button = findViewById(R.id.back)
        backButton.setOnClickListener{finish()}

        var companyName: TextView = findViewById(R.id.company)
        companyName.text = CalendarActivity.currJob.getCompanyName()

        var jobTitle: TextView = findViewById(R.id.title)
        jobTitle.text = CalendarActivity.currJob.getJobName()

        var dueDate: TextView = findViewById(R.id.date)
        var date: Triple<Int,Int,Int> = CalendarActivity.currJob.getDeadline()
        dueDate.text = "Due On " + (date.second).toString() + "/" + date.first.toString() + "/" + date.third.toString()
    }

}
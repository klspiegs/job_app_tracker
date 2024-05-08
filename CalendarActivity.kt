package com.example.final_436

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CalendarActivity: AppCompatActivity() {

    private var allJobs : ArrayList<Array<String>> = ArrayList()
    private var actualJobs : ArrayList<Job> = ArrayList()
    private lateinit var listView : ListView
    private lateinit var textView: TextView
    private lateinit var clickedJobs: ArrayList<Job>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        listView = findViewById(R.id.jobs)
        textView = findViewById(R.id.jobText)

        var calendarView: CalendarView = findViewById(R.id.calendar)
        //var calListener: CalListener = CalListener()
        //calendarView.setOnDateChangeListener(calListener)

        var homeButton: Button = findViewById(R.id.home)
        homeButton.setOnClickListener{finish()}

        var allButton: Button = findViewById(R.id.all)
        allButton.setOnClickListener{showAllJobs()}

        //Testing stuff, will just pull jobs from firebase
        // database stuff
        val sharedPreferences = getSharedPreferences("ReferenceNames", MODE_PRIVATE)
        val sharedPreferenceIds = sharedPreferences.all.map { it.value }
        // PERSISTENT DATA
        var firebase : FirebaseDatabase = FirebaseDatabase.getInstance()
        var listener = DataListener( )
        for (id in sharedPreferenceIds) {
            firebase.getReference((id.toString())).addValueEventListener( listener )
            Log.d("ID", id.toString())

        }

        //var reference : DatabaseReference = firebase.getReference( "JobInfo1" )
        //var testJob1: Job = Job()
        //testJob1.setJobName("Engineer")
        //testJob1.setCompanyName("Google")
        //testJob1.setDeadline(Triple(4,5,2024))
        //testJob1.setLocation("1600 Amphitheatre Parkway Mountain View California")
        //testJob1.setApplied(true)
        //reference.setValue( testJob1 )

        //var testJob2: Job = Job()
        //var reference2 : DatabaseReference = firebase.getReference( "JobInfo2" )
        //testJob2.setJobName("IT Technician")
        //.setCompanyName("Amazon")
        //testJob2.setDeadline(Triple(12,5,2024))
        //testJob2.setLocation("601 New Jersey Ave")
        //testJob2.setApplied(false)
        //reference2.setValue( testJob2 )


        //reference.addValueEventListener( listener )
        //reference2.addValueEventListener( listener )

        //var testJob1: Job = Job()




        updateJobs()

        showAllJobs()

    }

    inner class DataListener : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            Log.d("DL", "----LISTENING----")
            allJobs = ArrayList()
            var key :String? = snapshot.key

            Log.w( "MA", "key is " + key )
            var valueObject : Any? = snapshot.value
            if( valueObject != null ) {
                var value : String = valueObject.toString()
                allJobs.add(arrayOf(key.toString(), value))
                Log.w( "MA", "value is " + value )
            } else {
                Log.w( "MA", "No value found" )
            }
            printJobs()
            updateJobs()
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w( "MA", "reading failure: " + error.message )
        }

    }

    fun updateJobs() {
        for (job in allJobs){
            var tempJob = parseJobs(job[1])
            Log.d("IMPORTANT", tempJob.toString())
            actualJobs.add(tempJob)
        }
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        clickedJobs = actualJobs
        displayList(actualJobs)
    }

    fun printJobs() {
        for (job in allJobs) {
            Log.d("JOBS KEY", job[0])
            Log.d("JOBS VALUE", job[1])
        }
    }

    inner class ListItemHandler: AdapterView.OnItemClickListener {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            toJobPage(position)
        }
    }

    fun parseJobs(value : String) : Job {
        var job : Job = Job()
        var temp = value.split(",")
        lateinit var test : List<String>
        lateinit var temp2 : Triple<Int, Int, Int>
        var year : Int = 0
        var month : Int = 0
        var day : Int = 0

        // should have 7 things
        job.setJobName(temp[0].split("=")[1])
        job.setApplied(temp[1].split("=")[1] == "true")
        job.setCompanyName(temp[2].split("=")[1])
        job.setLocation(temp[3].split("=")[1])
        test = temp[4].split("=")
        Log.d("TEST", test.toString())
        year = (test[2]).toInt()
        month = (temp[5].split("=")[1]).toInt()
        day = (temp[6].split("=")[1]).replace("}", "").toInt()
        job.setDeadline(Triple(month, day, year))

        return job
    }

    inner class CalListener: CalendarView.OnDateChangeListener {
        override fun onSelectedDayChange(
            view: CalendarView,
            year: Int,
            month: Int,
            dayOfMonth: Int
        ) {
            var jobsOnDay: ArrayList<Job> = ArrayList()
            for(job in actualJobs){

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

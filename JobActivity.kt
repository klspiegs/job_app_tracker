package com.example.job_app_tracker

import android.location.Address
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@RequiresApi(Build.VERSION_CODES.TIRAMISU)

class JobActivity: AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var location: TextView
    private var latlong: LatLng = LatLng(0.0, 0.0)

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

        var appStatus: TextView = findViewById(R.id.status)
        if(CalendarActivity.currJob.getApplied()){
            appStatus.text = "Applied: Yes"
        }else{
            appStatus.text = "Applied: No"
        }

        location = findViewById(R.id.location)
        location.text = CalendarActivity.currJob.getLocation()

        var geocoder: Geocoder = Geocoder(this)
        var handler: GeocodeHandler = GeocodeHandler()
        repeat(3){
            geocoder.getFromLocationName(CalendarActivity.currJob.getLocation(), 10, handler)
        }



        var fragment:SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        var update: CameraUpdate = CameraUpdateFactory.newLatLngZoom(latlong, 18.0f)
        map.moveCamera(update)

        map.addMarker(MarkerOptions().position(latlong))

    }

    inner class GeocodeHandler: GeocodeListener {
        override fun onGeocode(addresses: MutableList<Address>) {
            latlong = LatLng(addresses.get(0).latitude, addresses.get(0).longitude)
        }

        override fun onError(errorMessage: String?) {
            super.onError(errorMessage)
            location.text = "Location Not Found"
            latlong = LatLng(0.0,0.0)
        }
    }

}
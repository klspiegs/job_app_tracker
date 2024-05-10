package com.example.job_app_tracker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.NightMode
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("modePrefs",
            Context.MODE_PRIVATE)
        var mode = sharedPreferences.getInt("darkMode", AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setDefaultNightMode(mode)



        // create an AdView
        var adView = AdView( this )
        var adSize : AdSize = AdSize( AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT )
        adView.setAdSize( adSize )
        var adUnitId : String = "ca-app-pub-3940256099942544/6300978111"
        adView.adUnitId = adUnitId
        // create an AdRequest
        var builder : AdRequest.Builder = AdRequest.Builder( )
        builder.addKeyword( "job" )
        var request : AdRequest = builder.build()
        // put the AdView in the LinearLayout
        var adLayout : LinearLayout = findViewById( R.id.adView )
        adLayout.addView( adView )
        // load the ad
        adView.loadAd( request )

//        var addBtn : Button = findViewById<Button>(R.id.addbtn)
//        addBtn.setOnClickListener() { toAddPage() }

        var calBtn : Button = findViewById<Button>(R.id.viewbtn)
        calBtn.setOnClickListener() { toCalendarPage() }

        var addBtn : Button = findViewById<Button>(R.id.addbtn)
        addBtn.setOnClickListener() { toAddPage() }

        var setBtn : Button = findViewById(R.id.settings)
        setBtn.setOnClickListener { toSettings() }

        //var firebase : FirebaseDatabase = FirebaseDatabase.getInstance()
        //var listener = DataListener()
        //val sharedPreferences = getSharedPreferences("ReferenceNames", MODE_PRIVATE)
        //val editor = sharedPreferences.edit()
        //editor.clear()
        //editor.apply()


    }







//    fun toAddPage() {
//        var myIntent : Intent = Intent(this, AddActivity::class.java)
//        startActivity(myIntent)
//    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun toCalendarPage() {
        var myIntent : Intent = Intent(this, CalendarActivity::class.java)
        startActivity(myIntent)
    }

    fun toAddPage() {
        var myIntent : Intent = Intent(this, DataActivity::class.java)
        startActivity(myIntent)
    }

    fun toSettings() {
        var myIntent : Intent = Intent(this, SettingsActivity::class.java)
        startActivity(myIntent)
    }
}

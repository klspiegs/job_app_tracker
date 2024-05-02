package com.example.groupproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private var ad : InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

//        var adUnitId : String = "ca-app-pub-3940256099942544/1033173711"
        var adRequest : AdRequest = AdRequest.Builder( ).build()
        var adLoad : AdLoad = AdLoad( )
        InterstitialAd.load( this, adUnitId, adRequest, adLoad )


    }

//    fun toAddPage() {
//        var myIntent : Intent = Intent(this, AddActivity::class.java)
//        startActivity(myIntent)
//    }

    fun toCalendarPage() {
        var myIntent : Intent = Intent(this, CalendarActivity::class.java)
        startActivity(myIntent)
    }

    inner class AdLoad : InterstitialAdLoadCallback( ) {
        override fun onAdLoaded(p0: InterstitialAd) {
            super.onAdLoaded(p0)
            // assign p0 to ad
            ad = p0
            // show the ad
            if( ad != null ) {
                ad!!.show(this@MainActivity)
                // manage the user interaction with the ad
                var manageAd : ManageAdShowing = ManageAdShowing()
                ad!!.fullScreenContentCallback = manageAd
            }

        }

        override fun onAdFailedToLoad(p0: LoadAdError) {
            super.onAdFailedToLoad(p0)
            Log.w( "MainActivity", "error loading the ad: " + p0.message )
        }
    }

    inner class ManageAdShowing : FullScreenContentCallback( ) {
        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            Log.w( "MainActivity", "Ad dismissed" )
        }

        override fun onAdClicked() {
            super.onAdClicked()
            Log.w( "MainActivity", "ad clicked"  )
        }

        override fun onAdImpression() {
            super.onAdImpression()
            Log.w( "MainActivity", "ad impressed" )
        }

        override fun onAdShowedFullScreenContent() {
            super.onAdShowedFullScreenContent()
            Log.w( "MainActivity", " ad shown" )
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            super.onAdFailedToShowFullScreenContent(p0)
            Log.w( "MainActivity", " ad failed to show" )
        }
    }
}
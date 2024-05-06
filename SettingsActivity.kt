package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var modeBtn = findViewById<Button>(R.id.mode)
        sharedPreferences = this.getSharedPreferences("modePrefs",
            Context.MODE_PRIVATE)
        var mode = sharedPreferences.getInt("darkMode", AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setDefaultNightMode(mode)
        if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
            modeBtn.text = "Switch to Light Mode"
        }
        Log.w("CAUGHT", "mode: $mode")


        modeBtn.setOnClickListener { switchModes(mode) }

        var backBtn = findViewById<Button>(R.id.exit)
        backBtn.setOnClickListener { finish() }


    }

    private fun switchModes(darkMode : Int) {
        var mode : Int = darkMode
        if (darkMode == AppCompatDelegate.MODE_NIGHT_YES) {
            mode = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            mode = AppCompatDelegate.MODE_NIGHT_YES
        }
        sharedPreferences.edit().putInt("darkMode", mode).apply()
        Log.w("SWITCH", "mode: $mode")
        recreate()
    }
}

package com.example.groupproject

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private lateinit var sets : Settings
//    private lateinit var sets : Settings
//    var nightMode : Int = AppCompatDelegate.getDefaultNightMode()
//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

//        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)
        sets = Settings(this)
        Log.w("SettingsActivity", sets.getMode().toString())

        if (sets.getMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        var modeBtn = findViewById<Button>(R.id.mode)
        modeBtn.setOnClickListener { switchModes(sets.getMode()) }

        var backBtn = findViewById<Button>(R.id.exit)
        backBtn.setOnClickListener { finish() }


    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        nightMode = AppCompatDelegate.getDefaultNightMode()
//        sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE)
//        editor = sharedPreferences.edit()
//        editor.putInt("nightMode", nightMode)
//        editor.apply()
//    }

    fun switchModes(darkMode : Boolean) {
        if (darkMode) {
            sets.setMode(this, false)
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            sets.setMode(this, true)
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        Log.w("SettingsActivity", "switch:" + sets.getMode().toString())
        recreate()
    }
}
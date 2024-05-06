package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color

class Settings {

    private var darkMode : Boolean = false

    constructor( context : Context ) {
        var pref : SharedPreferences = context.getSharedPreferences(context.packageName + "_preferences",
            Context.MODE_PRIVATE)
        setMode(context, pref.getBoolean(PREF_MODE, false))
    }

    fun getMode() : Boolean {
        return darkMode
    }

    fun setMode(context: Context, on : Boolean) {
        darkMode = on
        var sharedPreferences : SharedPreferences = context.getSharedPreferences(context.packageName + "_preferences",
            Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("darkMode", darkMode).apply()
    }

//    fun setPreferences( context : Context ) {
//        var sharedPreferences : SharedPreferences = context.getSharedPreferences(context.packageName + "_preferences",
//            Context.MODE_PRIVATE)
////        var edit : SharedPreferences.Editor = pref.edit()
////
////        edit.putBoolean(PREF_MODE, darkMode)
////
////        edit.commit()
//
//    }

    companion object {
        private const val PREF_MODE : String = "darkMode"
    }
}


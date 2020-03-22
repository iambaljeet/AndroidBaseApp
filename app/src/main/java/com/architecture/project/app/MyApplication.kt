package com.architecture.project.app

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication

class MyApplication: MultiDexApplication() {
    companion object {
        var sharedPreferences: SharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences("prefs", 0)
    }
}
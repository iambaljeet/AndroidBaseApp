package com.architecture.project.utility

import androidx.core.content.edit
import com.architecture.project.app.MyApplication.Companion.sharedPreferences

object PreferenceManager {
    fun <T>addToSharedPreference(key: String, value: T) {
        sharedPreferences?.edit(commit = true) {
            when(value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
            }
        }
    }

    fun getString(key: String, defaultValue: String) {
        sharedPreferences?.getString(key, defaultValue)
    }

    fun getInt(key: String, defaultValue: Int) {
        sharedPreferences?.getInt(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long) {
        sharedPreferences?.getLong(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean) {
        sharedPreferences?.getBoolean(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float) {
        sharedPreferences?.getFloat(key, defaultValue)
    }
}
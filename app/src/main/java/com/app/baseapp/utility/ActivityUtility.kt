package com.app.baseapp.utility

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

object ActivityUtility {
    /**
     * Extension method to provide hide keyboard for [AppCompatActivity].
     */
    fun AppCompatActivity.hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(
                Context
                    .INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}
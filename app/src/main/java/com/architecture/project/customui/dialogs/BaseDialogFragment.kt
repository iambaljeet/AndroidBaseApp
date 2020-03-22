package com.architecture.project.customui.dialogs

import android.app.Activity
import androidx.fragment.app.DialogFragment


open class BaseDialogFragment<T>: DialogFragment() {
    private var mActivityInstance: T? = null

    fun getActivityInstance(): T? {
        return mActivityInstance
    }

    override fun onAttach(activity: Activity) {
        mActivityInstance = activity as T
        super.onAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        mActivityInstance = null
    }
}
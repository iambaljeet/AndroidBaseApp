package com.architecture.project.customui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.architecture.project.R

class DialogWithTwoButtons: BaseDialogFragment<DialogWithTwoButtons.DialogEventsListener>() {

    // Create an instance of the Dialog with the input
    companion object {
        fun newInstance(
            title: String?, message: String?,
            positiveButtonText: String?, negativeButtonText: String?
        ): DialogWithTwoButtons? {
            return DialogWithTwoButtons().apply {
                arguments.apply {
                    val args = Bundle()
                    args.putString("title", title)
                    args.putString("message", message)
                    args.putString("positiveButtonText", positiveButtonText)
                    args.putString("negativeButtonText", negativeButtonText)
                    arguments = args
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.common_dialog_two_buttons_layout, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_dialog_bg)
        return view
    }

    interface DialogEventsListener
}
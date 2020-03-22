package com.architecture.project.customui

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class ButtonRegular: AppCompatTextView {
    constructor(context: Context?) : super(context){
        initView()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        typeface = Typeface.createFromAsset(context.assets, "Roboto-Regular.ttf")
    }
}
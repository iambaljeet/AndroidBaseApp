package com.architecture.project.customui

import android.content.Context
import android.content.res.TypedArray
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.architecture.project.R
import kotlinx.android.synthetic.main.shadow_edit_text_bg.view.*

class ShadowEditTextRegular: ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.shadow_edit_text_bg, this, true)

        val values: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadowEditTextRegular)

        val hintValue = values.getString(R.styleable.ShadowEditTextRegular_hint)
        val inputTypeValue = values.getInt(R.styleable.ShadowEditTextRegular_inputType, InputType.TYPE_CLASS_TEXT)
        val linesValue = values.getInt(R.styleable.ShadowEditTextRegular_lines, 0)
        val maxLengthValue = values.getInt(R.styleable.ShadowEditTextRegular_maxLength, 0)
        val maxLinesValue = values.getInt(R.styleable.ShadowEditTextRegular_maxLines, 0)
        val textValue = values.getString(R.styleable.ShadowEditTextRegular_text)
        val digitsValue = values.getString(R.styleable.ShadowEditTextRegular_digits)

        generalEditText.setText(textValue)
        generalEditText.hint = hintValue
        generalEditText.inputType = inputTypeValue

        if (!digitsValue.isNullOrBlank()) generalEditText.keyListener = DigitsKeyListener.getInstance(digitsValue)

        if (maxLinesValue > 0) generalEditText.maxLines = maxLinesValue
        if (linesValue > 0) generalEditText.setLines(linesValue)

        if (maxLengthValue > 0) {
            val filterArray = arrayOfNulls<InputFilter>(1)
            filterArray[0] = LengthFilter(maxLengthValue)
            generalEditText.filters = filterArray
        }

        if (maxLengthValue > 0) {
            val filterArray = arrayOfNulls<InputFilter>(1)
            filterArray[0] = LengthFilter(maxLengthValue)
            generalEditText.filters = filterArray
        }

        values.recycle()
    }
}
package com.app.baseapp.utility

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object ViewUtility {
    /**
     * Extension method to show a keyboard for View.
     */
    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        this.requestFocus()
        imm.showSoftInput(this, 0)
    }
    /**
     * Try to hide the keyboard and returns whether it worked
     * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
     */
    fun View.hideKeyboard(): Boolean {
        try {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        } catch (ignored: RuntimeException) { }
        return false
    }
    /**
     * Extension method to get value from EditText.
     */
    val EditText.value
        get() = text.toString()
    /**
     * Extension method to get value from AppCompatEditText.
     */
    val AppCompatEditText.value
        get() = text.toString()

    /**
     * Extension method to remove the required boilerplate for running code after a view has been
     * inflated and measured.
     *
     * @author Antonio Leiva
     * @see <a href="https://antonioleiva.com/kotlin-ongloballayoutlistener/>Kotlin recipes: OnGlobalLayoutListener</a>
     */
    fun <T : View> T.afterMeasured(f: T.() -> Unit) {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (measuredWidth > 0 && measuredHeight > 0) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    f()
                }
            }
        })
    }
    /**
     * Extension method to get ClickableSpan.
     * e.g.
     * val loginLink = getClickableSpan(context.getColorCompat(R.color.colorAccent), { })
     */
    fun View.doOnLayout(onLayout: (View) -> Boolean) {
        addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(view: View, left: Int, top: Int, right: Int, bottom: Int,
                                        oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                if (onLayout(view)) {
                    view.removeOnLayoutChangeListener(this)
                }
            }
        })
    }
    /**
     * Set an onclick listener
     */
    fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener { block(it as T) }
    /**
     * Extension method to set OnClickListener on a view.
     */
    fun <T : View> T.longClick(block: (T) -> Boolean) = setOnLongClickListener { block(it as T) }
    /**
     * Extension method to get a view as bitmap.
     */
    fun View.getBitmap(): Bitmap {
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        draw(canvas)
        canvas.save()
        return bmp
    }

    /**
     * Show a snackbar with [message]
     */
    fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) = snack(message, length) {}
    /**
     * Show a snackbar with [messageRes]
     */
    fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG) = snack(messageRes, length) {}
    /**
     * Show a snackbar with [message], execute [f] and show it
     */
    inline fun View.snack(message: String, @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
        val snack = Snackbar.make(this, message, length)
        snack.f()
        snack.show()
    }
    /**
     * Show a snackbar with [messageRes], execute [f] and show it
     */
    inline fun View.snack(@StringRes messageRes: Int, @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
        val snack = Snackbar.make(this, messageRes, length)
        snack.f()
        snack.show()
    }
}
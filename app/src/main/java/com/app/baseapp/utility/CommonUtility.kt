package com.app.baseapp.utility

import android.app.AlarmManager
import android.app.KeyguardManager
import android.app.Notification
import android.app.NotificationManager
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.content.ActivityNotFoundException
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.*
import android.content.Intent
import android.content.Intent.*
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.telephony.TelephonyManager
import android.util.Base64
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.*
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit

// Code from 'https://github.com/ravidsrk/kotlinextensions.com#view'

object CommonUtility {
    /**
     * Extension method to get the TAG name for all object
     */
    fun <T : Any> T.TAG() = this::class.simpleName
    /**
     * Extension method to find a device width in pixels
     */
    val Context.displayWidth: Int
        get() = resources.displayMetrics.widthPixels
    /**
     * Extension method to find a device height in pixels
     */
    val Context.displayHeight: Int
        get() = resources.displayMetrics.heightPixels
    /**
     * Extension method to get displayMetrics in Context.displayMetricks
     */
    val Context.displayMetrics: DisplayMetrics
        get() = resources.displayMetrics
    /**
     * Extension method to show long toast for Context.
     */
    fun Context.toastLong(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = Toast.makeText(this, text, duration).show()
    /**
     * Extension method to show long toast for Context.
     */
    fun Context.toastLong(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = Toast.makeText(this, textId, duration).show()
    /**
     * Extension method to show short toast for Context.
     */
    fun Context.toastShort(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration).show()
    /**
     * Extension method to show short toast for Context.
     */
    fun Context.toastShort(@StringRes textId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, textId, duration).show()
    /**
     * Extension method to Get Integer resource for Context.
     */
    fun Context.getInteger(@IntegerRes id: Int) = resources.getInteger(id)
    /**
     * Extension method to Get Boolean resource for Context.
     */
    fun Context.getBoolean(@BoolRes id: Int) = resources.getBoolean(id)
    /**
     * Extension method to Get Color for resource for Context.
     */
    fun Context.getColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)
    /**
     * Extension method to Get Drawable for resource for Context.
     */
    fun Context.getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)
    /**
     * InflateLayout
     */
    fun Context.inflateLayout(@LayoutRes layoutId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View
            = LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)
    /**
     * Extension method to get inputManager for Context.
     */
    val Context.inputManager: InputMethodManager?
        get() = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
    /**
     * Extension method to get notificationManager for Context.
     */
    val Context.notificationManager: NotificationManager?
        get() = getSystemService(NOTIFICATION_SERVICE) as? NotificationManager
    /**
     * Extension method to get keyguardManager for Context.
     */
    val Context.keyguardManager: KeyguardManager?
        get() = getSystemService(KEYGUARD_SERVICE) as? KeyguardManager
    /**
     * Extension method to get telephonyManager for Context.
     */
    val Context.telephonyManager: TelephonyManager?
        get() = getSystemService(TELEPHONY_SERVICE) as? TelephonyManager
    /**
     * Extension method to get devicePolicyManager for Context.
     */
    val Context.devicePolicyManager: DevicePolicyManager?
        get() = getSystemService(DEVICE_POLICY_SERVICE) as? DevicePolicyManager
    /**
     * Extension method to get connectivityManager for Context.
     */
    val Context.connectivityManager: ConnectivityManager?
        get() = getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager
    /**
     * Extension method to get alarmManager for Context.
     */
    val Context.alarmManager: AlarmManager?
        get() = getSystemService(ALARM_SERVICE) as? AlarmManager
    /**
     * Extension method to get clipboardManager for Context.
     */
    val Context.clipboardManager: ClipboardManager?
        get() = getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager
    /**
     * Extension method to get jobScheduler for Context.
     */
    val Context.jobScheduler: JobScheduler?
        get() = getSystemService(JOB_SCHEDULER_SERVICE) as? JobScheduler
    /**
     * Extension method to show notification for Context.
     */
    fun Context.notification(body: NotificationCompat.Builder.() -> Unit): Notification {
        val builder = NotificationCompat.Builder(this)
        builder.body()
        return builder.build()
    }
    /**
     * Extension method to share for Context.
     */
    fun Context.share(text: String, subject: String = ""): Boolean {
        val intent = Intent()
        intent.type = "text/plain"
        intent.putExtra(EXTRA_SUBJECT, subject)
        intent.putExtra(EXTRA_TEXT, text)
        try {
            startActivity(createChooser(intent, null))
            return true
        } catch (e: ActivityNotFoundException) {
            return false
        }
    }
    /**
     * Extension method to provide quicker access to the [LayoutInflater] from [Context].
     */
    fun Context.getLayoutInflater() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    /**
     * Extension method to display long toast text for Fragment.
     */
    fun Fragment?.toastLong(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = this?.let { activity?.toastLong(text, duration) }
    /**
     * Extension method to display long toast text for Fragment.
     */
    fun Fragment?.toastLong(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { activity?.toastLong(textId, duration) }
    /**
     * Extension method to display long toast text for Fragment.
     */
    fun Fragment?.toastShort(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = this?.let { activity?.toastShort(text, duration) }
    /**
     * Extension method to display long toast text for Fragment.
     */
    fun Fragment?.toastShort(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { activity?.toastShort(textId, duration) }
    /**
     * Extension method to share text for Fragment.
     */
    fun Fragment.share(text: String, subject: String = "") = activity?.share(text, subject)

    /**
     * Extension method to inflate layout for ViewGroup.
     */
    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    /**
     * Extension method to provide handler and mainThread.
     */
    private object ContextHandler {
        val handler = Handler(Looper.getMainLooper())
        val mainThread = Looper.getMainLooper().thread
    }
    /**
     * Extension method to run block of code on UI Thread.
     */
    fun runOnUiThread(action: () -> Unit){
        if (ContextHandler.mainThread == Thread.currentThread()) action() else ContextHandler.handler.post { action() }
    }
    /**
     * Extension method to run block of code after specific Delay.
     */
    fun runDelayed(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, action: () -> Unit) {
        Handler().postDelayed(action, timeUnit.toMillis(delay))
    }
    /**
     * Extension method to run block of code on UI Thread after specific Delay.
     */
    fun runDelayedOnUiThread(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, action: () -> Unit) {
        ContextHandler.handler.postDelayed(action, timeUnit.toMillis(delay))
    }

    /**
     * Extension method to check if String is Email.
     */
    fun String.isEmail(): Boolean {
        val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
        return matches(p)
    }
    /**
     * Extension method to check if String is Phone Number.
     */
    fun String.isPhone(): Boolean {
        val p = "^1([34578])\\d{9}\$".toRegex()
        return matches(p)
    }

    /**
     * Extension method to resize Bitmap to specified height and width.
     */
    fun Bitmap.resize(w: Number, h: Number): Bitmap {
        val width = width
        val height = height
        val scaleWidth = w.toFloat() / width
        val scaleHeight = h.toFloat() / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        if (width > 0 && height > 0) {
            return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
        }
        return this
    }
    /**
     * Extension method to save Bitmap to specified file path.
     */
    fun Bitmap.saveFile(path: String) {
        val f = File(path)
        if (!f.exists()) {
            f.createNewFile()
        }
        val stream = FileOutputStream(f)
        compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.flush()
        stream.close()
    }
    /**
     * Extension method to get base64 string for Bitmap.
     */
    fun Bitmap.toBase64(): String {
        var result = ""
        val baos = ByteArrayOutputStream()
        try {
            compress(Bitmap.CompressFormat.JPEG, 100, baos)
            baos.flush()
            baos.close()
            val bitmapBytes = baos.toByteArray()
            result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                baos.flush()
                baos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return result
    }
}
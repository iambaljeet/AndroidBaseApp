package com.app.baseapp.utility

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtility {
    /**
     * Extension method to get Date for String with specified format.
     */
    fun String.stringToDate(inputDateFormat: String): Date? {
        val dateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        var parsedDate: Date? = null
        try {
            parsedDate = dateFormat.parse(this)
        } catch (ignored: ParseException) {
            ignored.printStackTrace()
        }
        return parsedDate
    }

    /**
     * Extension method to get Date for String with specified format.
     */
    fun Date.dateToFormattedString(format: String): String? {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val today = Calendar.getInstance().time
        return dateFormat.format(today)
    }

    /**
     * Extension method to get Date for String with specified format.
     */
    @Throws(ParseException::class)
    fun String.formatDateFromDateString(
        inputDateFormat: String?, outputDateFormat: String?
    ): String? {
        val mParsedDate: Date?
        var mOutputDateString: String? = null
        val mInputDateFormat =
            SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val mOutputDateFormat =
            SimpleDateFormat(outputDateFormat, Locale.getDefault())
        mParsedDate = mInputDateFormat.parse(this)
        mParsedDate?.let { mOutputDateString = mOutputDateFormat.format(it) }
        return mOutputDateString
    }
}
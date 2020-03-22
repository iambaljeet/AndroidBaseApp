package com.architecture.project.utility

import java.text.SimpleDateFormat
import java.util.*

object DateUtility {
    //Format given date to return Date object
    fun stringDateToDate(dateFormatter: String, date: String): Date? {
        return SimpleDateFormat(dateFormatter).parse(date)
    }

    //Format given date object to given date format and returns formatted date as string
    fun formatDateToString(dateFormatter: String, date: Date?): String {
        val simpleDateFormat = SimpleDateFormat(dateFormatter)
        return simpleDateFormat.format(date)
    }
}
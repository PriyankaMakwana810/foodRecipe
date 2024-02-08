package com.tridya.foodrecipeblog.utils

import android.text.format.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object TimeStamp {
    const val FULL_DATE_FORMAT = "dd-MM-yyyy, hh:mm:ss a"
    const val FULL_DATE_FORMAT_SCHEDULE = "EEE dd MMM, hh:mm aa"
    const val DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy hh:mm"
    const val DATE_FORMAT_DDMMMYYYY = "dd MMM yyyy"
    const val DATE_FORMAT_MMDDYYYY = "MM/dd/yyyy"
    const val DATE_FORMAT_MMMDDYYYY = "MMM dd yyyy"
    const val DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd"
    const val DATE_FORMAT_MMDDYY = "MM/dd/yy"
    const val FULL_TIME_FORMAT = "hh:mm:ss a"
    const val SHORT_TIME_FORMAT = "hh:mm a"

    const val DATE_FORMAT = DATE_FORMAT_MMMDDYYYY
    const val DATE_FORMAT_NO_YEAR = "MMM dd"

    fun getCalendarInstance(): Calendar {
        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        return Calendar.getInstance(timeZone)
    }

    fun formatToSeconds(value: String?, format: String?): Long {
        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        return formatToSeconds(value, format, timeZone)
    }

    fun formatToSeconds(value: String?, format: String?, timeZone: TimeZone?): Long {
        try {
            val sdf = SimpleDateFormat(format, Locale.ENGLISH)
            if (timeZone != null) {
                sdf.timeZone = timeZone
            }
            val mDate = sdf.parse(value.toString())
            if (mDate != null) {
                return TimeUnit.MILLISECONDS.toSeconds(mDate.time)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    fun millisToFormat(millis: Long): String {
        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        return millisToFormat(millis, FULL_DATE_FORMAT, timeZone)
    }

    fun millisToFormat(millis: String): String {
        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        return millisToFormat(millis.toLong(), DATE_FORMAT_DDMMYYYY, timeZone)
    }

    fun millisToFormat(millis: Long, format: String?): String {
        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        return millisToFormat(millis, format, timeZone)
    }

    fun millisToFormat(millis: String, format: String?): String {
        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        return try {
            millisToFormat(millis.toLong(), format, timeZone)
        } catch (e: NumberFormatException) {
            millis
        }
    }

    fun millisToFormat(millis: String, format: String?, tz: TimeZone?): String {
        return try {
            millisToFormat(millis.toLong(), format, tz)
        } catch (e: NumberFormatException) {
            millis
        }
    }

    fun millisToFormat(millis: Long, format: String?, tz: TimeZone?): String {
        var milli = millis
        if (milli < 1000000000000L) {
            milli *= 1000
        }
        val cal = Calendar.getInstance(tz!!)
        cal.timeInMillis = milli
        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        sdf.timeZone = tz
        return sdf.format(cal.time)
    }

    fun getRelativeTime(timeMillis: Long): String {
        val nowMillis = System.currentTimeMillis()
        val timeDifferenceMillis = nowMillis - timeMillis

        return when {
            timeDifferenceMillis < TimeUnit.MINUTES.toMillis(1) -> "Just now"
            timeDifferenceMillis < TimeUnit.HOURS.toMillis(1) -> {
                val minutesAgo = TimeUnit.MILLISECONDS.toMinutes(timeDifferenceMillis)
                "$minutesAgo min ago"
            }

            timeDifferenceMillis < TimeUnit.DAYS.toMillis(1) -> {
                val hoursAgo = TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis)
                "$hoursAgo hr ago"
            }

            else -> getPastDayTime(timeMillis)
        }
    }

    fun getPastDayTime(timeInMillis: Long): String {
        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        var milli = timeInMillis
        if (milli < 1000000000000L) {
            milli *= 1000
        }
        val time = Calendar.getInstance(timeZone)
        time.timeInMillis = milli
        val now = Calendar.getInstance(timeZone)

        return if (now[Calendar.DATE] == time[Calendar.DATE]) {
            "Today"
        } else if (now[Calendar.DATE] - time[Calendar.DATE] == 1) {
            "Yesterday"
        } else if (now[Calendar.YEAR] == time[Calendar.YEAR]) {
            DateFormat.format(DATE_FORMAT_MMMDDYYYY, time).toString()
        } else {
            DateFormat.format(DATE_FORMAT_MMMDDYYYY, time).toString()
        }
    }

    fun getDateFromZuluTime(data: String?, format: String): String {
        if (data.isNullOrEmpty()) return ""
        try {
            val dateFormat: java.text.DateFormat = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                Locale.ENGLISH
            )
            val date: Date = dateFormat.parse(data) as Date
            val formatter: java.text.DateFormat = SimpleDateFormat(format, Locale.ENGLISH)
            formatter.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
            return formatter.format(date)
        } catch (e: ParseException) {
            return try {
                val dateFormat: java.text.DateFormat =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH)
                val date: Date = dateFormat.parse(data) as Date
                val formatter: java.text.DateFormat = SimpleDateFormat(format, Locale.ENGLISH)
                formatter.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
                formatter.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    fun getMillisFromZuluTime(data: String?): Long {
        if (data.isNullOrEmpty()) return 0
        val dateFormat: java.text.DateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            Locale.ENGLISH
        )
        dateFormat.timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        val date: Date = dateFormat.parse(data) as Date
        return date.time
    }

    fun getMillisFromZuluTimeStamp(data: String?): Long {
        if (data.isNullOrEmpty()) return 0
        val dateFormat: java.text.DateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            Locale.ENGLISH
        )
        dateFormat.timeZone = TimeZone.getDefault()
        val date: Date = dateFormat.parse(data) as Date
        return date.time
    }

    fun getZuluTimeFromTimestamp(data: Long): String {
        val timeZone = TimeZone.getTimeZone(TimeZone.getDefault().id)
        val cal = Calendar.getInstance(timeZone)
        cal.timeInMillis = data
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
        return sdf.format(cal.time)
    }
}
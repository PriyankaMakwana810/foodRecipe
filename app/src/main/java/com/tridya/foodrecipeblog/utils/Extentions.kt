package com.tridya.foodrecipeblog.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Locale

fun String.toToast(context: Context, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, length).show()
}

fun showShortToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

fun convertDateTimeToLong(dateTime: String): Long? {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US)
    return try {
        val inputDate = dateFormat.parse(dateTime)
        inputDate?.time
    } catch (e: Exception) {
        null
    }
}
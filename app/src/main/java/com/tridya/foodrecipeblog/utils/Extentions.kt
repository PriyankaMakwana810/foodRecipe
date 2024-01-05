package com.tridya.foodrecipeblog.utils

import android.content.Context
import android.widget.Toast

fun String.toToast(context: Context, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, length).show()
}

fun showShortToast(context: Context,string: String){
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}
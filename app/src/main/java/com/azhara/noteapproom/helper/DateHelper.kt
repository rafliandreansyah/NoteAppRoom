package com.azhara.noteapproom.helper

import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

object DateHelper {
    fun getDate(): String{
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}
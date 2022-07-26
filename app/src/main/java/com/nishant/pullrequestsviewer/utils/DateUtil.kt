package com.nishant.pullrequestsviewer.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
object DateUtil {

    private val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val output = SimpleDateFormat("dd-MM-yyyy")

    fun getParsedDate(inputDate: String?): String? {

        inputDate ?: return null

        try {
            val date = input.parse(inputDate)
            return date?.let { output.format(date) }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}
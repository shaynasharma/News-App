package com.byju.news

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Shayna Sharma on 12,June,2020
 */
class Utils {

    fun convertToFormatDate(publishedDate: String?): String {
        if(publishedDate == null){
            return ""
        }
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH)
        val date: Date = inputFormat.parse(publishedDate)
        return outputFormat.format(date)
    }
}
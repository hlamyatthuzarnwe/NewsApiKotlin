package com.wyne.newsapikotlin

import java.text.SimpleDateFormat
import java.util.*

fun toSimpleString(publishedAt : String) : String{

    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)//this come from postman method
    val outputFormatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm",Locale.getDefault())//EEE is week day

    val date = inputFormatter.parse(publishedAt)
    return outputFormatter.format(date)
}
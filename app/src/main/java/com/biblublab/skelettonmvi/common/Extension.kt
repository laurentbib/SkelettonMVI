package com.biblublab.skelettonmvi.common

import com.biblublab.data.common.EMPTY_STRING
import com.biblublab.data.common.FORMATTED_DATE
import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormattedDate() : String{
    val outFormat = SimpleDateFormat(FORMATTED_DATE, Locale.getDefault())
    return   outFormat.format(this)?: EMPTY_STRING
}
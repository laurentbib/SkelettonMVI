package com.biblublab.data.common

import android.os.Build
import android.widget.ImageView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toDate() : Date? = SimpleDateFormat(UTC_DATE_FORMAT, Locale.getDefault()).parse(this)


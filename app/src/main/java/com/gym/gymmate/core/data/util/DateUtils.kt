package com.gym.gymmate.core.data.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDateWithFormat(format: String): String {
    val sdf =
        SimpleDateFormat(format, Locale.getDefault(Locale.Category.FORMAT))
    return sdf.format(Date())
}
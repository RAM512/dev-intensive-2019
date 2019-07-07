package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 365 * DAY

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val timeDiff = date.time - this.time

    val years = timeDiff / YEAR
//    val months = calendar.get(Calendar.MONTH)
    val days = timeDiff / DAY
    val hours = timeDiff / HOUR
    val minutes = timeDiff / MINUTE

    return when {
        years > 0 -> "более года назад"
        years < 0 -> "более чем через год"
//        months > 0 -> return "${TimeUnits.MONTH.plural(months)} назад"
//        months < 0 -> return "через ${TimeUnits.MONTH.plural(months)}"
        days > 0 -> "${TimeUnits.DAY.plural(days.toInt())} назад"
        days < 0 -> "через ${TimeUnits.DAY.plural(days.toInt())}"
        hours > 0 -> "${TimeUnits.HOUR.plural(hours.toInt())} назад"
        hours < 0 -> "через ${TimeUnits.HOUR.plural(hours.toInt())}"
        minutes > 0 -> "${TimeUnits.MINUTE.plural(minutes.toInt())} назад"
        minutes < 0 -> "через ${TimeUnits.MINUTE.plural(minutes.toInt())}"
        timeDiff > 0 -> "несколько секунд назад"
        else -> "через несколько секунд"
    }

}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val absValue = abs(value)
        val is11to14 = absValue % 100 in 11..14
        val valMod10 = absValue % 10
        return when (this) {
            SECOND ->
                when {
                    is11to14 -> "$absValue секунд"
                    valMod10 == 1 -> "$absValue секунду"
                    valMod10 in 2..4 -> "$absValue секунды"
                    else -> "$absValue секунд"
                }
            MINUTE ->
                when {
                    is11to14 -> "$absValue минут"
                    valMod10 == 1 -> "$absValue минуту"
                    valMod10 in 2..4 -> "$absValue минуты"
                    else -> "$absValue минут"
                }
            HOUR ->
                when {
                    is11to14 -> "$absValue часов"
                    valMod10 == 1 -> "$absValue час"
                    valMod10 in 2..4 -> "$absValue часа"
                    else -> "$absValue часов"
                }
            DAY ->
                when {
                    is11to14 -> "$absValue дней"
                    valMod10 == 1 -> "$absValue день"
                    valMod10 in 2..4 -> "$absValue дня"
                    else -> "$absValue дней"
                }
        }
    }
}
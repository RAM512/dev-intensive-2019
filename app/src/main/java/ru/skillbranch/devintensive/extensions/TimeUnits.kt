package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs

fun TimeUnits.plural(value: Int): String {
    val absValue = abs(value)
    return when (this) {
        TimeUnits.SECOND ->
            if (absValue in 11..14)
                "$absValue секунд"
            else when (absValue % 10) {
                1 -> "секунду"
                in 2..4 -> "$absValue секунды"
                else -> "$absValue секунд"
            }
        TimeUnits.MINUTE ->
            if (absValue in 11..14)
                "$absValue минут"
            else when (absValue % 10) {
            1 -> "минуту"
            in 2..4 -> "$absValue минуты"
            else -> "$absValue минут"
        }
        TimeUnits.HOUR ->
            if (absValue in 11..14)
                "$absValue часов"
            else when (absValue % 10) {
            1 -> "час"
            in 2..4 -> "$absValue часа"
            else -> "$absValue часов"
        }
        TimeUnits.DAY ->
            if (absValue in 11..14)
                "$absValue дней"
            else when (absValue % 10) {
            1 -> "день"
            in 2..4 -> "$absValue дня"
            else -> "$absValue дней"
        }
    }
}
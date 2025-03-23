package it.xpug.kata.birthday_greetings

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class XDate(val date: LocalDate) {

    constructor(yyyyMMdd: String) : this(LocalDate.parse(yyyyMMdd, FORMATTER))

    val day: Int
        get() = date.dayOfMonth

    val month: Int
        get() = date.monthValue

    fun isSameDay(anotherDate: XDate): Boolean {
        return anotherDate.day == this.day && anotherDate.month == this.month
    }

    companion object {
        private val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    }
}

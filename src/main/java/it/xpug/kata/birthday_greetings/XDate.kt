package it.xpug.kata.birthday_greetings

import java.text.SimpleDateFormat
import java.util.*

class XDate {
    private val date: Date
    
    constructor(yyyyMMdd: String?) {
        if (yyyyMMdd.isNullOrBlank()) {
            date = Date()
        } else {
            date = SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd)
        }
    }

    val day: Int
        get() = getPartOfDate(GregorianCalendar.DAY_OF_MONTH)

    val month: Int
        get() = 1 + getPartOfDate(GregorianCalendar.MONTH)

    fun isSameDay(anotherDate: XDate): Boolean {
        return anotherDate.day == this.day && anotherDate.month == this.month
    }

    override fun hashCode(): Int {
        return date.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is XDate) return false
        return other.date == this.date
    }

    private fun getPartOfDate(part: Int): Int {
        val calendar = GregorianCalendar()
        calendar.time = date
        return calendar[part]
    }
}

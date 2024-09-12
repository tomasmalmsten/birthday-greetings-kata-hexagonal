package it.xpug.kata.birthday_greetings

import org.junit.Assert
import org.junit.Test

class EmployeeTest {
    @Test
    @Throws(Exception::class)
    fun testBirthday() {
        val employee = Employee("foo", "bar", "1990/01/31", "a@b.c")
        Assert.assertFalse("not his birthday", employee.isBirthday(XDate("2008/01/30")))
        Assert.assertTrue("his birthday", employee.isBirthday(XDate("2008/01/31")))
    }

    @Test
    @Throws(Exception::class)
    fun equality() {
        val base = Employee("First", "Last", "1999/09/01", "first@last.com")
        val same = Employee("First", "Last", "1999/09/01", "first@last.com")
        val different = Employee("First", "Last", "1999/09/01", "boom@boom.com")

        Assert.assertFalse(base == null)
        Assert.assertTrue(base == same)
        Assert.assertFalse(base == different)
    }
}

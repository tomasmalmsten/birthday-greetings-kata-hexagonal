package it.xpug.kata.birthday_greetings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EmployeeTest {
    @Test
    fun testBirthday() {
        val employee = Employee("foo", "bar", "1990/01/31", "a@b.c")

        assertThat(employee.isBirthday(XDate("2008/01/30")))
            .`as`("not his birthday")
            .isFalse()
        assertThat(employee.isBirthday(XDate("2008/01/31")))
            .`as`("his birthday")
            .isTrue()
    }
}

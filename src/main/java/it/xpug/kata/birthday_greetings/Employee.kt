package it.xpug.kata.birthday_greetings

data class Employee(
    val firstName: String?,
    val lastName: String?,
    val birthDate: String,
    val email: String?
) {

    private val xdate = XDate(birthDate)

    fun isBirthday(today: XDate): Boolean {
        return today.isSameDay(xdate)
    }

    override fun toString(): String {
        return "Employee $firstName $lastName <$email> born $birthDate"
    }
}

package it.xpug.kata.birthday_greetings

import java.io.IOException
import java.text.ParseException
import javax.mail.MessagingException
import javax.mail.internet.AddressException

object Main {
    @Throws(
        AddressException::class,
        IOException::class,
        ParseException::class,
        MessagingException::class
    )
    @JvmStatic
    fun main(args: Array<String>) {
        val service = BirthdayService()
        service.sendGreetings("employee_data.txt", XDate(), "localhost", 25)
    }
}

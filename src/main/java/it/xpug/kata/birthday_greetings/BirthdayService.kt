package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.text.ParseException
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class BirthdayService {
    fun sendGreetings(fileName: String, xDate: XDate, smtpHost: String, smtpPort: Int) {
        val `in` = BufferedReader(FileReader(fileName))
        var str = ""
        str = `in`.readLine() // skip header
        while ((`in`.readLine().also { str = it }) != null) {
            val employeeData = str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
            if (employee.isBirthday(xDate)) {
                val recipient = employee.email
                val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName!!)
                val subject = "Happy Birthday!"
                // Create a mail session
                val props = Properties()
                props["mail.smtp.host"] = smtpHost
                props["mail.smtp.port"] = "" + smtpPort
                val session = Session.getInstance(props, null)
                // Construct the message
                val msg: Message = MimeMessage(session)
                msg.setFrom(InternetAddress("sender@here.com"))
                msg.setRecipient(Message.RecipientType.TO, InternetAddress(recipient!!))
                msg.subject = subject
                msg.setText(body)
                // Send the message
                Transport.send(msg)
            }
        }
    }

}

package it.xpug.kata.birthday_greetings

import com.icegreen.greenmail.util.GreenMail
import com.icegreen.greenmail.util.GreenMailUtil
import com.icegreen.greenmail.util.ServerSetup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AcceptanceTest {
    private lateinit var mailServer: GreenMail
    private val birthdayService: BirthdayService = BirthdayService()

    @BeforeEach
    fun setUp() {
        mailServer = GreenMail(ServerSetup(NONSTANDARD_PORT, null, ServerSetup.PROTOCOL_SMTP))
        mailServer.start()
    }

    @AfterEach
    fun tearDown() {
        mailServer.stop()
    }

    @Test
    fun willSendGreetings_whenItsSomebodysBirthday() {
        birthdayService.sendGreetings("../employee_data.txt", XDate("2008/10/08"), "localhost", NONSTANDARD_PORT)

        assertThat(mailServer.receivedMessages.size)
            .`as`("message not sent?")
            .isEqualTo(1)

        val message = mailServer.receivedMessages[0]
        assertThat(GreenMailUtil.getBody(message))
            .isEqualTo("Happy Birthday, dear John!")
        assertThat(message.subject)
            .isEqualTo("Happy Birthday!")
        assertThat(message.allRecipients)
            .hasSize(1)
        assertThat(message.allRecipients[0].toString())
            .isEqualTo("john.doe@foobar.com")
    }

    @Test
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService.sendGreetings("../employee_data.txt", XDate("2008/01/01"), "localhost", NONSTANDARD_PORT)

        assertThat(mailServer.receivedMessages.size)
            .`as`("what? messages?")
            .isZero()
    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}

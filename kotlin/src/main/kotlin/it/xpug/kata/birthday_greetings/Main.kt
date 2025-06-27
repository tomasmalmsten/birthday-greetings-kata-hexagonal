package it.xpug.kata.birthday_greetings

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val service = BirthdayService()
        val date = args.get(0)
        service.sendGreetings("employee_data.txt", XDate(date), "localhost", 25)
    }
}

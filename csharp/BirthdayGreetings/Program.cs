using System;

namespace BirthdayGreetings
{
    class Program
    {
        static void Main(string[] args)
        {
            var service = new BirthdayService();
            var date = args[0];
            service.SendGreetings("../employee_data.txt", new XDate(date), "localhost", 25);
        }
    }
}

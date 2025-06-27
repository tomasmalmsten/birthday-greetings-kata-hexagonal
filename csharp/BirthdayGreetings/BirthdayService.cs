using System;
using System.IO;
using System.Net.Mail;

namespace BirthdayGreetings
{
    public class BirthdayService
    {
        public void SendGreetings(string fileName, XDate xDate, string smtpHost, int smtpPort)
        {
            using (var reader = new StreamReader(fileName))
            {
                reader.ReadLine(); // skip header
                string str;
                while ((str = reader.ReadLine()) != null)
                {
                    var employeeData = str.Split(", ");
                    var employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
                    if (employee.IsBirthday(xDate))
                    {
                        var recipient = employee.Email;
                        var body = $"Happy Birthday, dear {employee.FirstName}!";
                        var subject = "Happy Birthday!";
                        var message = new MailMessage("sender@here.com", recipient, subject, body);
                        using (var client = new SmtpClient(smtpHost, smtpPort))
                        {
                            client.Send(message);
                        }
                    }
                }
            }
        }
    }
} 
#!/usr/bin/env python3

import sys
import csv
import smtplib
from email.mime.text import MIMEText
from datetime import datetime

class XDate:
    def __init__(self, date_input):
        if isinstance(date_input, str):
            self.date = datetime.strptime(date_input.strip(), "%Y/%m/%d").date()
        else:
            self.date = date_input
    
    @property
    def day(self):
        return self.date.day
    
    @property
    def month(self):
        return self.date.month
    
    def is_same_day(self, another_date):
        return another_date.day == self.day and another_date.month == self.month
    
    def __str__(self):
        return str(self.date)

class Employee:
    def __init__(self, firstName, lastName, birthDate, email):
        self.firstName = firstName
        self.lastName = lastName
        self.xdate = XDate(birthDate)
        self.email = email
    
    def is_birthday(self, today):
        return today.is_same_day(self.xdate)
    
    def __str__(self):
        return f"Employee {self.firstName} {self.lastName} <{self.email}> born {self.xdate}"

class BirthdayService:
    def send_greetings(self, fileName, xDate, smtpHost, smtpPort):
        with open(fileName, 'r') as file:
            reader = csv.reader(file)
            next(reader)  # skip header
            
            for row in reader:
                # Strip whitespace from all fields
                employee = Employee(row[1].strip(), row[0].strip(), row[2].strip(), row[3].strip())
                
                if employee.is_birthday(xDate):
                    recipient = employee.email
                    body = f"Happy Birthday, dear {employee.firstName}!"
                    subject = "Happy Birthday!"
                    
                    # Create message
                    msg = MIMEText(body)
                    msg['Subject'] = subject
                    msg['From'] = "sender@here.com"
                    msg['To'] = recipient
                    
                    # Send message
                    with smtplib.SMTP(smtpHost, smtpPort) as server:
                        server.send_message(msg)

def main():
    if len(sys.argv) != 2:
        print("Usage: python main.py <date>")
        print("Example: python main.py 2008/10/08")
        sys.exit(1)
    
    date = sys.argv[1]
    print(f"Birthday greetings service - checking for birthdays on {date}")
    
    service = BirthdayService()
    service.send_greetings("employee_data.txt", XDate(date), "localhost", 25)

if __name__ == "__main__":
    main() 
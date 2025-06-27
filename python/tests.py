#!/usr/bin/env python3

import unittest
from unittest.mock import patch, MagicMock
from main import XDate, Employee, BirthdayService

class XDateTest(unittest.TestCase):
    def test_getters(self):
        date = XDate("1789/01/24")
        self.assertEqual(date.month, 1)
        self.assertEqual(date.day, 24)
    
    def test_is_same_date(self):
        date = XDate("1789/01/24")
        same_day = XDate("2001/01/24")
        not_same_day = XDate("1789/01/25")
        not_same_month = XDate("1789/02/25")
        
        self.assertTrue(date.is_same_day(same_day), "same")
        self.assertFalse(date.is_same_day(not_same_day), "not same day")
        self.assertFalse(date.is_same_day(not_same_month), "not same month")

class EmployeeTest(unittest.TestCase):
    def test_birthday(self):
        employee = Employee("foo", "bar", "1990/01/31", "a@b.c")
        
        self.assertFalse(employee.is_birthday(XDate("2008/01/30")), "not his birthday")
        self.assertTrue(employee.is_birthday(XDate("2008/01/31")), "his birthday")

class AcceptanceTest(unittest.TestCase):
    def setUp(self):
        self.birthday_service = BirthdayService()
    
    @patch('smtplib.SMTP')
    def test_will_send_greetings_when_its_somebodys_birthday(self, mock_smtp):
        # Arrange
        mock_server = MagicMock()
        mock_smtp.return_value.__enter__.return_value = mock_server
        
        # Act
        self.birthday_service.send_greetings("../employee_data.txt", XDate("2008/10/08"), "localhost", 9999)
        
        # Assert
        # Verify SMTP was called with correct parameters
        mock_smtp.assert_called_once_with("localhost", 9999)
        
        # Verify send_message was called once (one email sent)
        self.assertEqual(mock_server.send_message.call_count, 1)
        
        # Get the message that was sent
        sent_message = mock_server.send_message.call_args[0][0]
        
        # Verify email content
        self.assertEqual(sent_message['Subject'], "Happy Birthday!")
        self.assertEqual(sent_message['From'], "sender@here.com")
        self.assertEqual(sent_message['To'], "john.doe@foobar.com")
        self.assertEqual(sent_message.get_payload(), "Happy Birthday, dear John!")
    
    @patch('smtplib.SMTP')
    def test_will_not_send_emails_when_nobodys_birthday(self, mock_smtp):
        # Arrange
        mock_server = MagicMock()
        mock_smtp.return_value.__enter__.return_value = mock_server
        
        # Act
        self.birthday_service.send_greetings("../employee_data.txt", XDate("2008/01/01"), "localhost", 9999)
        
        # Assert
        # Verify SMTP was NOT called since no emails were sent
        mock_smtp.assert_not_called()
        
        # Verify no messages were sent
        self.assertEqual(mock_server.send_message.call_count, 0)

if __name__ == '__main__':
    unittest.main() 
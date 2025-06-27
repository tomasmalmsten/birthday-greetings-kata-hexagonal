namespace BirthdayGreetings
{
    public class Employee
    {
        public string FirstName { get; }
        public string LastName { get; }
        private XDate xdate;
        public string Email { get; }

        public Employee(string firstName, string lastName, string birthDate, string email)
        {
            FirstName = firstName;
            LastName = lastName;
            xdate = new XDate(birthDate);
            Email = email;
        }

        public bool IsBirthday(XDate today)
        {
            return today.IsSameDay(xdate);
        }

        public override string ToString()
        {
            return $"Employee {FirstName} {LastName} <{Email}> born {xdate}";
        }
    }
} 
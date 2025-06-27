using NUnit.Framework;
using BirthdayGreetings;

namespace BirthdayGreetings.Tests
{
    [TestFixture]
    public class EmployeeTest
    {
        [Test]
        public void TestBirthday()
        {
            var employee = new Employee("foo", "bar", "1990/01/31", "a@b.c");

            Assert.That(employee.IsBirthday(new XDate("2008/01/30")), Is.False, "not his birthday");
            Assert.That(employee.IsBirthday(new XDate("2008/01/31")), Is.True, "his birthday");
        }
    }
} 
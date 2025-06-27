using NUnit.Framework;
using BirthdayGreetings;

namespace BirthdayGreetings.Tests
{
    [TestFixture]
    public class XDateTest
    {
        [Test]
        public void Getters()
        {
            var date = new XDate("1789/01/24");
            Assert.That(date.Month, Is.EqualTo(1));
            Assert.That(date.Day, Is.EqualTo(24));
        }

        [Test]
        public void IsSameDate()
        {
            var date = new XDate("1789/01/24");
            var sameDay = new XDate("2001/01/24");
            var notSameDay = new XDate("1789/01/25");
            var notSameMonth = new XDate("1789/02/25");

            Assert.That(date.IsSameDay(sameDay), Is.True, "same");
            Assert.That(date.IsSameDay(notSameDay), Is.False, "not same day");
            Assert.That(date.IsSameDay(notSameMonth), Is.False, "not same month");
        }
    }
} 
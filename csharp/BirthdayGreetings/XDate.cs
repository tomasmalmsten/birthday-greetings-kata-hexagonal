using System;
using System.Globalization;

namespace BirthdayGreetings
{
    public class XDate
    {
        private DateTime date;

        public XDate(string yyyyMMdd)
        {
            date = DateTime.ParseExact(yyyyMMdd, "yyyy/MM/dd", CultureInfo.InvariantCulture);
        }

        public int Day => date.Day;
        public int Month => date.Month;

        public bool IsSameDay(XDate anotherDate)
        {
            return anotherDate.Day == this.Day && anotherDate.Month == this.Month;
        }

        public override string ToString()
        {
            return date.ToString("yyyy-MM-dd");
        }
    }
} 
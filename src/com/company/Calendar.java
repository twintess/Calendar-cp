package com.company;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Calendar{
    private final LocalDate today;
    private LocalDate calendarDay; //current month
    private final static int console_width = 20;

    public Calendar()
    {
        this.today = LocalDate.now();
        this.calendarDay = LocalDate.of(today.getYear(), today.getMonth(), 1);
    }

    public void print() {
        System.out.println(this.printMonth(calendarDay));
    }

    public void nextMonth()
    {
        this.calendarDay = this.calendarDay.plusMonths(1);
    }

    public void previousMonth()
    {
        this.calendarDay = this.calendarDay.minusMonths(1);
    }

    private StringBuilder printMonth(LocalDate tempDay)
    {
        Month month = this.calendarDay.getMonth();
        StringBuilder strBuilder = new StringBuilder(initCalendar());

        for(int i = 0; i < tempDay.getDayOfWeek().getValue() - 1; i++)
            strBuilder.append("   ");

        int month_length = setLeap();

        //add days of month
        for(int i = 0; i < month_length; i++)
        {
            strBuilder.append(tempDay.getDayOfMonth());
            if(Integer.toString(tempDay.getDayOfMonth()).length() == 1)
                strBuilder.append("  ");
            else strBuilder.append(" ");
            tempDay = tempDay.plusDays(1);

            if(tempDay.getDayOfWeek() == DayOfWeek.MONDAY)
                strBuilder.append('\n');
        }

        return strBuilder;
    }

    private int setLeap()
    {
        Month month = this.calendarDay.getMonth();
        int length = month.maxLength();
        if(month == Month.FEBRUARY && !this.calendarDay.isLeapYear())
            return length - 1;
        else return length;
    }

    private StringBuilder initCalendar() //add month name, year and days of week
    {
        Month month = this.calendarDay.getMonth();
        int spaces = (console_width - month.toString().length() - 5) /2;
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < spaces; i++)
        {
            result.append(" ");
        }

        result.append(month + " " + this.calendarDay.getYear() + '\n');

        //add days of week
        for(int i = 0; i < 7; i++)
        {
            result.append(DayOfWeek.of(i+1).toString().charAt(0) + "  ");
        }

        result.append('\n');
        return result;
    }
}

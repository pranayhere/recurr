package com.we.recurr;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoUnit.DAYS;

public class LocalDateTimeTest {
    public static void main(String[] args) {
        int interval = 2;
        LocalDateTime date = LocalDateTime.of(2020, 4, 23, 9, 8, 7);
        LocalDateTime lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime secondMonday = date.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));


        date = date.plusMonths(13);
        System.out.println(date.toString());
        System.out.println("lastDay : " + lastDay.toString());
        System.out.println("secondMonday : " + secondMonday.toString());
        date = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime nextTuesday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        LocalDateTime nextMonday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

        try {
            LocalDateTime validDate = date.withDayOfMonth(-3);
            System.out.println();
        } catch (DateTimeException e) {
            System.out.println(e);
        }
        LocalDateTime thirtyFirstOfEveryMonth = date.withDayOfMonth(1);
        System.out.println(date);
        System.out.println("Next Tuesday : " + nextTuesday);
        System.out.println("Next Monday : " + nextMonday);
        System.out.println("thirtyFirstOfEveryMonth : " + thirtyFirstOfEveryMonth);

        LocalDateTime todayNew = LocalDateTime.of(2020, 4, 19, 9, 8, 7);
        System.out.println("Day of month : " + todayNew.getDayOfMonth());
//        System.out.println(todayNew.withDayOfMonth(31));
        LocalDateTime ordinalDate = dayOfWeekInMonth(-1, DayOfWeek.FRIDAY, todayNew);
        System.out.println("ordinal date : " + ordinalDate);

    }

    public static LocalDateTime dayOfWeekInMonth(int ordinal, DayOfWeek dayOfWeek, LocalDateTime today) {
        int dowValue = dayOfWeek.getValue();
        if (ordinal > 0) {
            Temporal temp = today.with(DAY_OF_MONTH, 1);
            int curDow = temp.get(DAY_OF_WEEK);
            int dowDiff = (dowValue - curDow + 7) % 7;
            dowDiff += (ordinal - 1L) * 7L;  // safe from overflow
            return (LocalDateTime) temp.plus(dowDiff, DAYS);
        } else {
            Temporal temp = today.with(DAY_OF_MONTH, today.range(DAY_OF_MONTH).getMaximum());
            int curDow = temp.get(DAY_OF_WEEK);
            int daysDiff = dowValue - curDow;
            daysDiff = (daysDiff == 0 ? 0 : (daysDiff > 0 ? daysDiff - 7 : daysDiff));
            daysDiff -= (-ordinal - 1L) * 7L;  // safe from overflow
            return (LocalDateTime) temp.plus(daysDiff, DAYS);
        }
    }
}
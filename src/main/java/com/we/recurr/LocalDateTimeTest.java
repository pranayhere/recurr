package com.we.recurr;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

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
    }
}

package com.we.recurr.strategies;

import com.we.recurr.iter.RecurrenceIterator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MonthlyIteratorStrategyTest {

    private final LocalDateTime today = LocalDateTime.of(2020, 5, 5, 9, 15, 20);

    @Test
    public void everyMonth() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 5, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 5, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyTwoMonthOnFirstDay() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=1;INTERVAL=2;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 7, 1, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 9, 1, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 11, 1, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyMonthFromStartOfTheMonth() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=1,31;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 31, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 1, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 1, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 7, 31, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 8, 1, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyMonthFromEndOfTheMonth() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=-1,-31;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 31, 9, 15, 20), days.get(0));


        assertEquals(LocalDateTime.of(2020, 6, 30, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 1, 9, 15, 20), days.get(2));

        assertEquals(LocalDateTime.of(2020, 7, 31, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 8, 1, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyMonthDatesMixed() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=-2,10;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 10, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 30, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 6, 10, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 6, 29, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 7, 10, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyMonthOnLastDayOfTheMonth() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=-1;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 31, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 30, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 31, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyMonthOn1stAnd15thOfTheMonth() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=1,15;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 15, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 1, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 6, 15, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 7, 1, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 7, 15, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyMonthOnLastDayOnLeapYear() {
        LocalDateTime today = LocalDateTime.of(2020, 1, 5, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=-1;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 1, 31, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 2, 29, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 3, 31, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyMonthOnLastDayOnNonLeapYear() {
        LocalDateTime today = LocalDateTime.of(2019, 1, 5, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=-1;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2019, 1, 31, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2019, 2, 28, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2019, 3, 31, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyMonthOnLastDayOnEndOfYear() {
        LocalDateTime today = LocalDateTime.of(2020, 11, 5, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=-1;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 11, 30, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 12, 31, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2021, 1, 31, 9, 15, 20), days.get(2));
    }


    // Monthly By Weekdays
    @Test
    public void everyMonthOnFriday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 8, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 15, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 22, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 5, 29, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 6, 5, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyMonthOnFirstFriday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=1FR;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 6, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 7, 3, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 8, 7, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyTwoMonthOnFirstThursday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=1TH;INTERVAL=2;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 7, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 7, 2, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 9, 3, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyMonthOnLastFriday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=-1FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        System.out.println(days);
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 29, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 26, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 31, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 8, 28, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 9, 25, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyMonthOnFirstThursdayAndSecondLastWednesday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=1TH,-2WE;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 7, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 20, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 6, 4, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 6, 17, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 7, 2, 9, 15, 20), days.get(4));
    }
}

package com.we.recurr.examples;

import com.we.recurr.iter.RecurrenceIterator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DailyTest {

    private final LocalDateTime today = LocalDateTime.of(2020, 5, 5, 9, 15, 20);

    @Test
    public void everyday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=DAILY;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 6, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 7, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyTwoDays() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=DAILY;INTERVAL=2;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 7, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 9, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyDayMonthEnd() {
        LocalDateTime today = LocalDateTime.of(2020, 5, 30, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=DAILY;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 30, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 31, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 6, 1, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyDayYearEnd() {
        LocalDateTime today = LocalDateTime.of(2020, 12, 30, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=DAILY;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 12, 30, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 12, 31, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2021, 1, 1, 9, 15, 20), days.get(2));
    }

    @Test
    public void everySevenDays() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=DAILY;INTERVAL=7;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 12, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 19, 9, 15, 20), days.get(2));
    }

    @Test
    public void dailyOnALeapYear() {
        LocalDateTime today = LocalDateTime.of(2020, 2, 27, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=DAILY;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 2, 27, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 2, 28, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 2, 29, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 3, 1, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 3, 2, 9, 15, 20), days.get(4));
    }

    @Test
    public void dailyOnANonLeapYear() {
        LocalDateTime today = LocalDateTime.of(2019, 2, 27, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=DAILY;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2019, 2, 27, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2019, 2, 28, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2019, 3, 1, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2019, 3, 2, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2019, 3, 3, 9, 15, 20), days.get(4));
    }
}

package com.we.recurr.examples;

import com.we.recurr.iter.RecurrenceIterator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WeeklyTest {

    private final LocalDateTime today = LocalDateTime.of(2020, 5, 4, 0, 0, 0);

    @Test
    public void everyWeek() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 4, 0, 0, 0, 0), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 11, 0, 0, 0, 0), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 18, 0, 0, 0, 0), days.get(2));
    }

    @Test
    public void everyTwoWeeks() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;INTERVAL=2;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 4, 0, 0, 0, 0), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 18, 0, 0, 0, 0), days.get(1));
        assertEquals(LocalDateTime.of(2020, 6, 1, 0, 0, 0, 0), days.get(2));
    }

    @Test
    public void everyWeekOnMOndayAndThursday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;BYDAY=MO,TH;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 4, 0, 0, 0, 0), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 7, 0, 0, 0, 0), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 11, 0, 0, 0, 0), days.get(2));
    }

    @Test
    public void everyTwoWeeksOnWeekdays() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;BYDAY=MO,TH;INTERVAL=2;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 4, 0, 0, 0, 0), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 7, 0, 0, 0, 0), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 18, 0, 0, 0, 0), days.get(2));
    }

    @Test
    public void everyWeekOnWeekdays() {
        LocalDateTime today = LocalDateTime.of(2020, 5, 6, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;BYDAY=MO,TU,WE,TH,FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 6, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 7, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 8, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 5, 11, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 5, 12, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyWeekOnWeekend() {
        LocalDateTime today = LocalDateTime.of(2020, 5, 6, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;BYDAY=SA,SU;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 9, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 10, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 16, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 5, 17, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 5, 23, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyWeekOnWeekdaysOnMonthEnd() {
        LocalDateTime today = LocalDateTime.of(2020, 6, 29, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;BYDAY=MO,TU,WE,TH,FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 6, 29, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 30, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 1, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 7, 2, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 7, 3, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyWeekOnWeekdaysYearEnd() {
        LocalDateTime today = LocalDateTime.of(2020, 12, 29, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;BYDAY=MO,TU,WE,TH,FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 12, 29, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 12, 30, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 12, 31, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2021, 1, 1, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2021, 1, 4, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyWeekOnLeapYear() {
        LocalDateTime today = LocalDateTime.of(2020, 2, 27, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;BYDAY=MO,TU,WE,TH,FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        System.out.println(days);
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 2, 27, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 2, 28, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 3, 2, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2020, 3, 3, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2020, 3, 4, 9, 15, 20), days.get(4));
    }

    @Test
    public void everyWeekOnNonLeapYear() {
        LocalDateTime today = LocalDateTime.of(2019, 2, 27, 9, 15, 20);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;BYDAY=MO,TU,WE,TH,FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        System.out.println(days);
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2019, 2, 27, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2019, 2, 28, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2019, 3, 1, 9, 15, 20), days.get(2));
        assertEquals(LocalDateTime.of(2019, 3, 4, 9, 15, 20), days.get(3));
        assertEquals(LocalDateTime.of(2019, 3, 5, 9, 15, 20), days.get(4));
    }
}

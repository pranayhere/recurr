package com.we.recurr.examples;

import com.we.recurr.iter.RecurrenceIterator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MonthlyTest {

    private final LocalDateTime today = LocalDateTime.of(2020, 5, 5, 0, 0, 0);

    @Test
    public void monthlyDates() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 0, 0, 0, 0), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 5, 0, 0, 0, 0), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 5, 0, 0, 0, 0), days.get(2));
    }

    @Test
    public void monthlyFromStartOfTheMonth() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=1,31;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 31, 0, 0, 0, 0), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 1, 0, 0, 0, 0), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 1, 0, 0, 0, 0), days.get(2));
        assertEquals(LocalDateTime.of(2020, 7, 31, 0, 0, 0, 0), days.get(3));
        assertEquals(LocalDateTime.of(2020, 8, 1, 0, 0, 0, 0), days.get(4));
    }

    @Test
    public void monthlyFromEndOfTheMonth() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=-1,-31;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 31, 0, 0, 0, 0), days.get(0));
        assertEquals(LocalDateTime.of(2020, 6, 30, 0, 0, 0, 0), days.get(1));
        assertEquals(LocalDateTime.of(2020, 7, 1, 0, 0, 0, 0), days.get(2));
        assertEquals(LocalDateTime.of(2020, 7, 31, 0, 0, 0, 0), days.get(3));
        assertEquals(LocalDateTime.of(2020, 8, 1, 0, 0, 0, 0), days.get(4));
    }

    @Test
    public void monthlyDatesMixed() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYMONTHDAY=-2,10;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        assertEquals(5, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 10, 0, 0, 0, 0), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 30, 0, 0, 0, 0), days.get(1));
        assertEquals(LocalDateTime.of(2020, 6, 10, 0, 0, 0, 0), days.get(2));
        assertEquals(LocalDateTime.of(2020, 6, 29, 0, 0, 0, 0), days.get(3));
        assertEquals(LocalDateTime.of(2020, 7, 10, 0, 0, 0, 0), days.get(4));
    }

    @Test
    public void monthlyOnFriday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
    }

    @Test
    public void monthlyOnFirstFriday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=1FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
    }

    @Test
    public void monthlyOnLastFriday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=-1FR;COUNT=5", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
    }

    @Test
    public void monthlyOnFirstMondayAndSecondLastFriday() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=1MO,-2FR;COUNT=10", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
    }
}

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
    public void weeklyDates() {
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
    public void weeklyWithInterval() {
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
    public void weeklyWithWeekdays() {
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
}

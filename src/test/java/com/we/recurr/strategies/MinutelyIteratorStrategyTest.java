package com.we.recurr.strategies;

import com.we.recurr.iter.RecurrenceIterator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MinutelyIteratorStrategyTest {
    private final LocalDateTime today = LocalDateTime.of(2020, 5, 5, 9, 15, 20);

    @Test
    public void everyMinute() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MINUTELY;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 16, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 17, 20), days.get(2));
    }

    @Test
    public void everyFiveMinute() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MINUTELY;INTERVAL=5;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 20, 25), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 25, 30), days.get(2));
    }

    @Test
    public void everyTwentiethAndThirtiethMinute() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MINUTELY;BYMINUTE=20,30;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }

        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 30), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 16, 20), days.get(2));
    }


}

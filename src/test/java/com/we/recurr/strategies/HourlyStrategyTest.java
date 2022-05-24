package com.we.recurr.strategies;

import com.we.recurr.iter.RecurrenceIterator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HourlyStrategyTest {

    private final LocalDateTime today = LocalDateTime.of(2020, 5, 5, 9, 15, 20);

    @Test
    public void everHour() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=HOURLY;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 5, 10, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 5, 11, 15, 20), days.get(2));
    }

    @Test
    public void everyIntervalHour() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=HOURLY;INTERVAL=24;UNTIL=20200507T111000Z", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 6, 9, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 7, 9, 15, 20), days.get(2));
    }

    @Test
    public void everyIntervalHourByStartDate() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=HOURLY;INTERVAL=3;DTSTART=20200509T091520Z;COUNT=3", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(3, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 9, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 9, 12, 15, 20), days.get(1));
        assertEquals(LocalDateTime.of(2020, 5, 9, 15, 15, 20), days.get(2));
    }

    @Test
    public void everyIntervalHourByEndDate() {
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=HOURLY;INTERVAL=2;UNTIL=20200505T121000Z", today);
        List<LocalDateTime> days = new ArrayList<>();
        while (itr.hasNext()) {
            days.add(itr.next());
        }
        System.out.println(days);
        assertEquals(2, days.size());
        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), days.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 5, 11, 15, 20), days.get(1));
    }
}

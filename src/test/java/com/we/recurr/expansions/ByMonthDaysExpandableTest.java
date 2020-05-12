package com.we.recurr.expansions;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ByMonthDaysExpandableTest {

    private final LocalDateTime today = LocalDateTime.of(2020, 5, 5, 9, 15, 20);

    @Test
    public void shouldReturnSameDaysIfMonthDaysIsEmpty() {
        List<LocalDateTime> tt = Collections.singletonList(today);
        List<Integer> monthDays = new ArrayList<>();
        List<LocalDateTime> dates = new ByMonthDaysExpandable(tt, monthDays).expand();

        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), dates.get(0));
    }

    @Test
    public void shouldReturnDatesFromStartWhenMonthDaysArePositive() {
        List<LocalDateTime> tt = Collections.singletonList(today);
        List<Integer> monthDays = Arrays.asList(10, 15);
        List<LocalDateTime> dates = new ByMonthDaysExpandable(tt, monthDays).expand();

        assertEquals(2, dates.size());
        assertEquals(LocalDateTime.of(2020, 5, 10, 9, 15, 20), dates.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 15, 9, 15, 20), dates.get(1));
    }

    @Test
    public void shouldReturnDatesFromEndWhenMonthDaysAreNegative() {
        List<LocalDateTime> tt = Collections.singletonList(today);
        List<Integer> monthDays = Arrays.asList(-1, -5);
        List<LocalDateTime> dates = new ByMonthDaysExpandable(tt, monthDays).expand();

        assertEquals(2, dates.size());
        System.out.println(dates);
        assertEquals(LocalDateTime.of(2020, 5, 27, 9, 15, 20), dates.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 31, 9, 15, 20), dates.get(1));
    }

}

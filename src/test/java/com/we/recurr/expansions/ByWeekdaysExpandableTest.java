package com.we.recurr.expansions;

import com.we.recurr.domain.QualifiedWeekday;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ByWeekdaysExpandableTest {

    private final LocalDateTime today = LocalDateTime.of(2020, 5, 5, 9, 15, 20);
    private final List<LocalDateTime> tt = Collections.singletonList(today);
    private final List<QualifiedWeekday> weekdays = Arrays.asList(new QualifiedWeekday(0, DayOfWeek.THURSDAY), new QualifiedWeekday(0, DayOfWeek.FRIDAY));

    @Test
    public void shouldReturnSameDaysIfWeekdaysIsEmpty() {
        List<QualifiedWeekday> weekdays = new ArrayList<>();
        List<LocalDateTime> dates = new ByWeekdaysExpandable(tt, DayOfWeek.MONDAY, weekdays).expand();

        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), dates.get(0));
    }

    @Test
    public void shouldReturnDatesOnTheGivenWeekdays() {
        List<LocalDateTime> dates = new ByWeekdaysExpandable(tt, DayOfWeek.MONDAY, weekdays).expand();

        assertEquals(2, dates.size());
        System.out.println(dates);
        assertEquals(LocalDateTime.of(2020, 5, 7, 9, 15, 20), dates.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 8, 9, 15, 20), dates.get(1));
    }

    @Test
    public void shouldReturnDateOnStartOfWeek() {
        ByWeekdaysExpandable ex = new ByWeekdaysExpandable(tt, DayOfWeek.MONDAY, weekdays);
        LocalDateTime startOfWeek = ex.backToWeekday(today, DayOfWeek.MONDAY);

        assertEquals(LocalDateTime.of(2020, 5, 4, 9, 15, 20), startOfWeek);
    }

    @Test
    public void shouldReturnDateOnNextMonday() {
        ByWeekdaysExpandable ex = new ByWeekdaysExpandable(tt, DayOfWeek.MONDAY, weekdays);
        LocalDateTime forwardDate = ex.forwardToWeekday(today, DayOfWeek.MONDAY);

        System.out.println(forwardDate);
        assertEquals(LocalDateTime.of(2020, 5, 11, 9, 15, 20), forwardDate);
    }
}

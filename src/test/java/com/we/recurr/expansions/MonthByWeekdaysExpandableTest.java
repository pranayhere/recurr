package com.we.recurr.expansions;

import com.we.recurr.domain.QualifiedWeekday;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MonthByWeekdaysExpandableTest {

    private final LocalDateTime today = LocalDateTime.of(2020, 5, 5, 9, 15, 20);
    private final LocalDateTime lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
    private final List<LocalDateTime> tt = Collections.singletonList(today);
    private final List<QualifiedWeekday> positiveWeekdays = Arrays.asList(new QualifiedWeekday(1, DayOfWeek.THURSDAY), new QualifiedWeekday(2, DayOfWeek.FRIDAY));
    private final List<QualifiedWeekday> negativeWeekdays = Arrays.asList(new QualifiedWeekday(-1, DayOfWeek.THURSDAY), new QualifiedWeekday(-2, DayOfWeek.FRIDAY));

    @Test
    public void shouldReturnSameDaysIfWeekdaysIsEmpty() {
        List<QualifiedWeekday> weekdays = new ArrayList<>();
        List<LocalDateTime> dates = new MonthByWeekdaysExpandable(tt, weekdays).expand();

        assertEquals(LocalDateTime.of(2020, 5, 5, 9, 15, 20), dates.get(0));
    }

    @Test
    public void shouldReturnWeekdayInMonthWhenPositiveOrdinal() {
        List<LocalDateTime> dates = new MonthByWeekdaysExpandable(tt, positiveWeekdays).expand();

        assertEquals(2, dates.size());
        assertEquals(LocalDateTime.of(2020, 5, 7, 9, 15, 20), dates.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 8, 9, 15, 20), dates.get(1));
    }

    @Test
    public void shouldReturnWeekdayInMonthWhenNegativeOrdinal() {
        List<LocalDateTime> dates = new MonthByWeekdaysExpandable(tt, negativeWeekdays).expand();

        System.out.println(dates);
        assertEquals(2, dates.size());
        assertEquals(LocalDateTime.of(2020, 5, 22, 9, 15, 20), dates.get(0));
        assertEquals(LocalDateTime.of(2020, 5, 28, 9, 15, 20), dates.get(1));
    }

    @Test
    public void shouldReturnNumberOfThursdayInMonth() {
        MonthByWeekdaysExpandable ex = new MonthByWeekdaysExpandable(tt, positiveWeekdays);
        int noOfWeekdays = ex.countWeekdaysInMonth(DayOfWeek.THURSDAY, lastDayOfMonth);

        assertEquals(4, noOfWeekdays);
    }

    @Test
    public void shouldReturnNumberOfFridayInMonth() {
        MonthByWeekdaysExpandable ex = new MonthByWeekdaysExpandable(tt, positiveWeekdays);
        int noOfWeekdays = ex.countWeekdaysInMonth(DayOfWeek.FRIDAY, lastDayOfMonth);

        assertEquals(5, noOfWeekdays);
    }
}

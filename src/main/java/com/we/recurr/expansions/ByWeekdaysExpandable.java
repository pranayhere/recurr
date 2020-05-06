package com.we.recurr.expansions;

import com.we.recurr.domain.QualifiedWeekday;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class ByWeekdaysExpandable implements Expandable {

    private List<LocalDateTime> tt;
    private DayOfWeek weekStart;
    private List<QualifiedWeekday> weekdays;

    public ByWeekdaysExpandable(List<LocalDateTime> tt, DayOfWeek weekStart, List<QualifiedWeekday> weekdays) {
        this.tt = new ArrayList<>(tt);
        this.weekStart = weekStart != null ? weekStart : DayOfWeek.MONDAY;
        this.weekdays = new ArrayList<>(weekdays);
    }

    @Override
    public List<LocalDateTime> expand() {
        if (weekdays.isEmpty()) {
            return tt;
        }

        List<LocalDateTime> e = new ArrayList<>();
        for (LocalDateTime t: tt) {
            t = backToWeekDay(t, this.weekStart);
            for (QualifiedWeekday weekday: weekdays) {
                e.add(forwardToWeekday(t, weekday.getWeekday()));
            }
        }
        return e;
    }

    private LocalDateTime forwardToWeekday(LocalDateTime t, DayOfWeek day) {
        return t.with(TemporalAdjusters.nextOrSame(day));
    }

    private LocalDateTime backToWeekDay(LocalDateTime t, DayOfWeek weekStart) {
        return t.with(TemporalAdjusters.previousOrSame(weekStart));
    }
}

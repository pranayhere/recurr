package com.we.recurr.expansions;

import com.we.recurr.domain.QualifiedWeekday;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonthByWeekdaysExpandable implements Expandable {

    private List<LocalDateTime> tt;
    private List<QualifiedWeekday> weekdays;

    public MonthByWeekdaysExpandable(List<LocalDateTime> tt, List<QualifiedWeekday> weekdays) {
        this.tt = new ArrayList<>(tt);
        this.weekdays = new ArrayList<>(weekdays);
    }

    @Override
    public List<LocalDateTime> expand() {
        if (weekdays.isEmpty()) {
            return tt;
        }

        List<LocalDateTime> e = new ArrayList<>();
        for (LocalDateTime t: tt) {
            e.addAll(weekdaysInMonth(t, weekdays));
        }

        return e;
    }

    protected List<LocalDateTime> weekdaysInMonth(LocalDateTime t, List<QualifiedWeekday> weekdays) {
        LocalDateTime firstDay = t.with(TemporalAdjusters.firstDayOfMonth());
        DayOfWeek firstWeekday = firstDay.getDayOfWeek();
        LocalDateTime lastDay = t.with(TemporalAdjusters.lastDayOfMonth());
        int lastDate = lastDay.getDayOfMonth();

        List<Integer> dates = new ArrayList<>();

        for (QualifiedWeekday weekday: weekdays) {
            int countOfWD = countWeekdaysInMonth(weekday.getWeekday(), lastDay);

            if (weekday.getOrdinal() == 0) {
                int daysTill = daysTill(firstWeekday, weekday.getWeekday());
                for (int i = 0; i<countOfWD; i++) {
                    int date = i * 7 + daysTill;
                    if (date <= lastDate) {
                        dates.add(date);
                    }
                }
            }
            if (weekday.getOrdinal() > 0) {
                int daysTill = daysTill(firstWeekday, weekday.getWeekday());
                int date = ((weekday.getOrdinal() - 1) * 7) + daysTill;
                if (date <= lastDay.getDayOfMonth()) {
                    dates.add(date);
                }
            }

            if (weekday.getOrdinal() < 0) {
                int needWDBefore = lastDay.getDayOfMonth() + (7 * (weekday.getOrdinal() + 1));
                int date = needWDBefore - daysFrom(lastDay.getDayOfWeek(), weekday.getWeekday()) - 1;
                if (date > 0) {
                    dates.add(date);
                }
            }
        }

        Collections.sort(dates);

        List<LocalDateTime> out = new ArrayList<>();
        for (Integer date: dates) {
            try {
                LocalDateTime expandedDate = firstDay.plusDays(date);
                out.add(expandedDate);
            } catch (Exception ex) {
                System.out.println("exception in MonthByWeekDays : " + ex);
            }
        }
        return out;
    }

    protected int daysTill(DayOfWeek from, DayOfWeek to) {
        if (from.getValue() == to.getValue()) {
            return 0;
        }
        int diff = to.getValue() - from.getValue();
        if (diff < 0) {
            diff += 7;
        }
        return diff;
    }

    protected int countWeekdaysInMonth(DayOfWeek wd, LocalDateTime lastDayOfMonth) {
        int lastDate = lastDayOfMonth.getDayOfMonth();
        DayOfWeek lastWD = lastDayOfMonth.getDayOfWeek();

        int daysBack = daysFrom(lastWD, wd);
        if (daysBack < (lastDate - 28)) {
            return 5;
        }
        return 4;
    }

    protected int daysFrom(DayOfWeek startFrom, DayOfWeek backTo) {
        if (startFrom.getValue() == backTo.getValue()) {
            return 0;
        }

        int diff = startFrom.getValue() - backTo.getValue();
        if (diff < 0) {
            diff += 7;
        }

        return diff;
    }
}

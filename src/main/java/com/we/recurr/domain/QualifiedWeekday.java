package com.we.recurr.domain;

import java.time.DayOfWeek;

public class QualifiedWeekday {
    private int ordinal;
    private DayOfWeek weekday;

    public QualifiedWeekday(int ordinal, DayOfWeek weekday) {
        this.ordinal = ordinal;
        this.weekday = weekday;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public DayOfWeek getWeekday() {
        return weekday;
    }
}

package com.we.recurr.domain;

import java.time.DayOfWeek;

public class QualifiedWeekday {
    private int ordinal;
    private DayOfWeek day;

    public QualifiedWeekday(int ordinal, DayOfWeek day) {
        this.ordinal = ordinal;
        this.day = day;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public DayOfWeek getDay() {
        return day;
    }
}

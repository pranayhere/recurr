package com.we.recurr.domain;

import sun.plugin.dom.exception.InvalidStateException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class RRule {
    private int interval;
    private int count;
    private DayOfWeek weekStart;
    private Frequency frequency;
    private LocalDateTime until;
    private LocalDateTime dtStart;
    private List<QualifiedWeekday> byWeekdays;
    private List<Integer> byMonthDays;
    private List<Month> byMonths;

    public RRule() {
        this.byWeekdays = new ArrayList<>();
        this.byMonthDays = new ArrayList<>();
        this.byMonths = new ArrayList<>();
    }

    public void validate() {
        if (frequency != Frequency.MONTHLY) {
            for (QualifiedWeekday wd: byWeekdays) {
                if (wd.getOrdinal() != 0) {
                    throw new InvalidStateException("BYDAY entries may only specify a numeric component when the frequency is YEARLY or MONTHLY");
                }
            }
        }

        if (frequency == Frequency.WEEKLY && byMonthDays.size() > 0) {
            throw new InvalidStateException("WEEKLY recurrences must not include BYMONTHDAY");
        }

        if (count != 0 && until != null) {
            throw new InvalidStateException("COUNT and UNTIL must not appear in the same RRULE");
        }
    }

    public DayOfWeek getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(DayOfWeek weekStart) {
        this.weekStart = weekStart;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getUntil() {
        return until;
    }

    public void setUntil(LocalDateTime until) {
        this.until = until;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getDtStart() {
        return dtStart;
    }

    public void setDtStart(LocalDateTime dtStart) {
        this.dtStart = dtStart;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<QualifiedWeekday> getByWeekdays() {
        return byWeekdays;
    }

    public void setByWeekdays(List<QualifiedWeekday> byWeekdays) {
        this.byWeekdays = byWeekdays;
    }

    public List<Integer> getByMonthDays() {
        return byMonthDays;
    }

    public void setByMonthDays(List<Integer> byMonthDays) {
        this.byMonthDays = byMonthDays;
    }

    public List<Month> getByMonths() {
        return byMonths;
    }

    public void setByMonths(List<Month> byMonths) {
        this.byMonths = byMonths;
    }

    @Override
    public String toString() {
        return "RRule{" +
                "frequency=" + frequency +
                ", until=" + until +
                ", count=" + count +
                ", dtStart=" + dtStart +
                ", interval=" + interval +
                ", byWeekdays=" + byWeekdays +
                ", byMonthDays=" + byMonthDays +
                ", byMonths=" + byMonths +
                '}';
    }
}

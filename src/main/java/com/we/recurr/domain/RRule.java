package com.we.recurr.domain;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class RRule {
    private Frequency frequency;
    private LocalDateTime until;
    private int count;
    private LocalDateTime dtStart;
    private int interval;
    private List<QualifiedWeekday> byWeekdays;
    private List<Integer> byMonthDays;
    private List<Month> byMonths;

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

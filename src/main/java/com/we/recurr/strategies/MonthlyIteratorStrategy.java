package com.we.recurr.strategies;

import com.we.recurr.domain.RRule;
import com.we.recurr.expansions.ByMonthDaysExpandable;
import com.we.recurr.expansions.MonthByWeekdaysExpandable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonthlyIteratorStrategy extends IteratorStrategy {

    private RRule rrule;
    private LocalDateTime current;
    int interval = 1;

    public MonthlyIteratorStrategy(RRule rrule) {
        this.rrule = rrule;
        this.qCap = rrule.getCount();
        LocalDateTime start = rrule.getDtStart();
        if (start == null) {
            start = LocalDateTime.now();
        }

        if (rrule.getInterval() != 0) {
            interval = rrule.getInterval();
        }

        this.current = start;
        this.minTime = start;
        this.maxTime = timeOrZero(rrule.getUntil());
    }

    @Override
    public LocalDateTime next() {
        LocalDateTime ret = current;
        current = current.plusMonths(interval);
        return ret;
    }

    @Override
    public boolean valid(LocalDateTime localDateTime) {
        return true;
    }

    @Override
    public List<LocalDateTime> variations(LocalDateTime t) {
        List<LocalDateTime> tt = new ArrayList<>(Collections.singletonList(t));
        List<LocalDateTime> variations = new ArrayList<>();
        if (!rrule.getByMonthDays().isEmpty()) {
            variations.addAll(new ByMonthDaysExpandable(tt, rrule.getByMonthDays()).expand());
        } else if (!rrule.getByWeekdays().isEmpty()) {
            variations.addAll(new MonthByWeekdaysExpandable(tt, rrule.getByWeekdays()).expand());
        } else {
            variations.addAll(tt);
        }
        return variations;
    }
}
package com.we.recurr.strategies;

import com.we.recurr.domain.RRule;
import com.we.recurr.expansions.ByWeekdaysExpandable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeeklyIteratorStrategy extends IteratorStrategy {

    private RRule rrule;
    private LocalDateTime start;
    private LocalDateTime current;
    int interval = 1;

    public WeeklyIteratorStrategy(RRule rrule) {
        this.rrule = rrule;
        this.qCap = rrule.getCount();
        this.start = rrule.getDtStart();
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
        current = current.plusWeeks(interval);
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
        variations.addAll(new ByWeekdaysExpandable(tt, rrule.getWeekStart(), rrule.getByWeekdays()).expand());
        return variations;
    }
}

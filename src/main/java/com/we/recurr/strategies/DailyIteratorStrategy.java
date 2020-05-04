package com.we.recurr.strategies;

import com.we.recurr.domain.RRule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DailyIteratorStrategy extends IteratorStrategy {

    private LocalDateTime start;
    private LocalDateTime current;
    private int interval = 1;

    public DailyIteratorStrategy(RRule rrule) {
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
        current = current.plusDays(interval);
        return ret;
    }

    @Override
    public boolean valid(LocalDateTime key) {
        return true;
    }

    public List<LocalDateTime> variations(LocalDateTime t) {
        return new ArrayList<>(Collections.singletonList(t));
    }
}

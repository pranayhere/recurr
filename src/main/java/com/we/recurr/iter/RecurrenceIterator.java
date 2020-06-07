package com.we.recurr.iter;

import com.we.recurr.domain.RRule;
import com.we.recurr.parser.RRuleParser;
import com.we.recurr.strategies.DailyIteratorStrategy;
import com.we.recurr.strategies.IteratorStrategy;
import com.we.recurr.strategies.MonthlyIteratorStrategy;
import com.we.recurr.strategies.MinutelyIteratorStrategy;
import com.we.recurr.strategies.WeeklyIteratorStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecurrenceIterator implements Iterator<LocalDateTime> {
    private RRule rrule;
    private IteratorStrategy iteratorStrategy;

    public RecurrenceIterator(String rRuleString, LocalDateTime startDate) {
        this.rrule = new RRuleParser(rRuleString, startDate).parse();
        this.iteratorStrategy = this.iteratorStrategy();
    }

    @Override
    public boolean hasNext() {
        return this.peek() != null;
    }

    public LocalDateTime peek() {
        IteratorStrategy i = this.getIteratorStrategy();
        if (!i.getQueue().isEmpty()) {
            return i.getQueue().peek();
        }

        if (i.getqCap() > 0) {
            if (i.getTotalQueued() > i.getqCap()) {
                return null;
            }
        }

        // start logic here
        for (;;) {
            if (i.isPastMaxTime()) {
                return null;
            }

            LocalDateTime key = i.next();
            if (key == null) {
                return null;
            }

            if (!i.valid(key)) {
                continue;
            }

            // remove variations before minTime
            List<LocalDateTime> variations = new ArrayList<>(i.variations(key));
            while (!variations.isEmpty() && variations.get(0).isBefore(i.getMinTime())) {
                variations.remove(0);
            }

            // remove variations after maxTime
            if (i.getMaxTime() != null) {
                for (int k = 0; k<variations.size();) {
                    LocalDateTime v = variations.get(k);
                    if (v.isAfter(i.getMaxTime())) {
                        variations.remove(k);
                        i.setPastMaxTime(true);
                        break;
                    }
                    k++;
                }
            }

            if (variations.isEmpty()) {
                continue;
            }

            // create sublist if variations are more than the queue cap
            if (i.getqCap() > 0) {
                if (i.getTotalQueued() + variations.size() > i.getqCap()) {
                    variations = variations.subList(0, i.getqCap() - i.getTotalQueued());
                }
            }

            i.setTotalQueued(i.getTotalQueued() + variations.size());
            i.getQueue().addAll(variations);
            return !variations.isEmpty() ? variations.get(0) : null;
        }
    }

    @Override
    public LocalDateTime next() {
        IteratorStrategy i = this.getIteratorStrategy();
        LocalDateTime time = this.peek();
        if (!i.getQueue().isEmpty()) {
            i.getQueue().poll();
        }
        return time;
    }

    private IteratorStrategy iteratorStrategy() {
        switch (rrule.getFrequency()) {
            case MINUTELY:
                return new MinutelyIteratorStrategy(rrule);
            case DAILY:
                return new DailyIteratorStrategy(rrule);
            case WEEKLY:
                return new WeeklyIteratorStrategy(rrule);
            case MONTHLY:
                return new MonthlyIteratorStrategy(rrule);
            default:
                throw new IllegalArgumentException("Strategy doesn't exists for : " + rrule.getFrequency());
        }
    }

    public RRule getRRule() {
        return rrule;
    }

    public IteratorStrategy getIteratorStrategy() {
        return iteratorStrategy;
    }

}
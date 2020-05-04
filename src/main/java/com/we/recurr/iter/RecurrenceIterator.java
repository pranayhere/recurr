package com.we.recurr.iter;

import com.we.recurr.domain.RRule;
import com.we.recurr.parser.RRuleParser;
import com.we.recurr.strategies.DailyIteratorStrategy;
import com.we.recurr.strategies.IteratorStrategy;
import com.we.recurr.strategies.WeeklyIteratorStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecurrenceIterator implements Iterator<LocalDateTime> {
    private RRule rrule;
    private IteratorStrategy i;

    public RecurrenceIterator(String rRuleString, LocalDateTime startDate) {
        this.rrule = new RRuleParser(rRuleString, startDate).parse();
        this.i = this.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.peek() != null;
    }

    public LocalDateTime peek() {
        if (!i.q.isEmpty()) {
            return i.q.peek();
        }

        if (i.qCap > 0) {
            if (i.totalQueued > i.qCap) {
                return null;
            }
        }

        // start logic here
        for (;;) {
            if (i.pastMaxTime) {
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
            if (!variations.isEmpty() && variations.get(0).isBefore(i.minTime)) {
                variations.remove(0);
            }

            // remove variations after maxTime
            if (i.maxTime != null) {
                for (int k = 0; k<variations.size();) {
                    LocalDateTime v = variations.get(k);
                    if (v.isAfter(i.maxTime)) {
                        variations.remove(k);
                        i.pastMaxTime = true;
                        break;
                    }
                    k++;
                }
            }

            if (variations.isEmpty()) {
                continue;
            }

            // create sublist if variations are more than the queue cap
            if (i.qCap > 0) {
                if (i.totalQueued + variations.size() > i.qCap) {
                    variations = variations.subList(0, i.qCap - i.totalQueued);
                }
            }

            i.totalQueued += variations.size();
            i.q.addAll(variations);
            return !variations.isEmpty() ? variations.get(0) : null;
        }
    }

    @Override
    public LocalDateTime next() {
        LocalDateTime time = this.peek();
        if (!i.q.isEmpty()) {
            i.q.poll();
        }
        return time;
    }

    private IteratorStrategy iterator() {
        switch (rrule.getFrequency()) {
            case DAILY:
                return new DailyIteratorStrategy(rrule);
            case WEEKLY:
                return new WeeklyIteratorStrategy(rrule);
            default:
                throw new IllegalArgumentException("Strategy doesn't exists for : " + rrule.getFrequency());
        }
    }
}
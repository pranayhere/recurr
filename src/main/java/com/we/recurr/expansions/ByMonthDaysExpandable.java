package com.we.recurr.expansions;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ByMonthDaysExpandable implements Expandable {
    private List<LocalDateTime> tt;
    private List<Integer> monthDays;

    public ByMonthDaysExpandable(List<LocalDateTime> tt, List<Integer> monthDays) {
        this.tt = new ArrayList<>(tt);
        this.monthDays = new ArrayList<>(monthDays);
    }

    @Override
    public List<LocalDateTime> expand() {
        if (monthDays.isEmpty()) {
            return tt;
        }

        List<LocalDateTime> e = new ArrayList<>();
        for (LocalDateTime t: tt) {
            LocalDateTime firstDay = t.with(TemporalAdjusters.firstDayOfMonth());
            LocalDateTime lastDay = t.with(TemporalAdjusters.lastDayOfMonth());
            for (Integer monthDay: monthDays) {
                LocalDateTime date;
                if (Math.abs(monthDay) > lastDay.getDayOfMonth()) {
                    continue;
                }

                // note: we have allowed date range from -1 to -31 and 1 to 31, refer RRuleParser.java
                if (monthDay > 0) {
                    date = firstDay.plusDays(monthDay - 1);
                } else {
                    date = lastDay.plusDays(monthDay + 1);
                }
                e.add(date);
            }
        }
        Collections.sort(e);
        return e;
    }
}
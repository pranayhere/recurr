package com.we.recurr.expansions;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ByMinutesExpandable implements Expandable {

    private List<LocalDateTime> tt;
    private List<Integer> minutes;

    public ByMinutesExpandable(List<LocalDateTime> tt, List<Integer> minutes) {
        this.tt = new ArrayList<>(tt);
        this.minutes = new ArrayList<>(minutes);
    }

    @Override
    public List<LocalDateTime> expand() {
        if (minutes.isEmpty()) {
            return tt;
        }

        List<LocalDateTime> e = new ArrayList<>();
        for (LocalDateTime t: tt) {
            LocalDateTime startOfHour = t.minusMinutes(t.getMinute());
            for (int minute: minutes) {
                if (minute < 0) {
                    minute += 60;
                }
                LocalDateTime date = startOfHour.plusMinutes(minute);
                e.add(date);
            }
        }
        Collections.sort(e);
        return e;
    }
}

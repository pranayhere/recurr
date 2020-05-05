package com.we.recurr.expansions;

import com.we.recurr.domain.QualifiedWeekday;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MonthByWeekdaysExpandable implements Expandable {

    private List<LocalDateTime> tt;
    private List<QualifiedWeekday> weekdays;

    public MonthByWeekdaysExpandable(List<LocalDateTime> tt, List<QualifiedWeekday> weekdays) {
        this.tt = new ArrayList<>(tt);
        this.weekdays = new ArrayList<>(weekdays);
    }

    @Override
    public List<LocalDateTime> expand() {
        return null;
    }
}

package com.we.recurr.iter;

import com.we.recurr.domain.RRule;
import com.we.recurr.parser.RRuleParser;

import java.time.LocalDate;
import java.util.Iterator;

public class RecurringDateIterator implements Iterator<LocalDate> {
    private String rruleString;
    private LocalDate startDate;
    private RRule rrule;

    private static int COUNT = 0;

    public RecurringDateIterator(String rruleString, LocalDate startDate) {
        this.rruleString = rruleString;
        this.startDate = startDate;
        this.rrule = new RRuleParser(this.rruleString).parse();
        System.out.println(rrule.toString());
    }

    @Override
    public boolean hasNext() {
        COUNT++;
        System.out.println("has next : " + COUNT);
        return COUNT <= 10;
    }

    @Override
    public LocalDate next() {
        System.out.println("generating next : " + COUNT);
        return null;
    }
}

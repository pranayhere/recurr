package com.we.recurr;

import com.we.recurr.iter.RecurringDateIterator;

import java.time.LocalDate;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        Iterator<LocalDate> itr = new RecurringDateIterator("RRULE:FREQ=DAILY;COUNT=3", today);
        while (itr.hasNext()) {
            itr.next();
        }
    }
}
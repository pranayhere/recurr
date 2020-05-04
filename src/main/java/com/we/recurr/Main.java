package com.we.recurr;

import com.we.recurr.iter.RecurrenceIterator;

import java.time.LocalDateTime;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now().withNano(0);
//        LocalDateTime today = LocalDateTime.of(2020, 5, 4, 0, 0, 0);
        Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=WEEKLY;COUNT=3", today);
        while (itr.hasNext()) {
            LocalDateTime date = itr.next();
            System.out.println(date);
        }
    }
}
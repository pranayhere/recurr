package com.we.recurr.iter;

import java.time.LocalDate;
import java.util.Iterator;

public class RecurringDateIterator implements Iterator<LocalDate> {
	private String rrule;
	private LocalDate startDate;

	private static int COUNT = 0;
	public RecurringDateIterator(String rrule, LocalDate startDate) {
		this.rrule = rrule;
		this.startDate = startDate;
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

package com.we.recurr.parser;

import com.we.recurr.domain.Frequency;
import com.we.recurr.domain.QualifiedWeekday;
import org.junit.Before;
import org.junit.Test;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;

public class RRuleParserTest {

    private RRuleParser rruleParser;

    @Before
    public void init() {
        rruleParser = new RRuleParser();
    }

    @Test
    public void shouldParseGivenMonth() {
        List<Month> actual = rruleParser.parseMonths("1,3");
        List<Month> expected = Arrays.asList(Month.JANUARY, Month.MARCH);

        assertThat(actual, is(expected));
    }

    @Test(expected = DateTimeException.class)
    public void shouldThrowExceptionOnIncorrectMonth() {
        List<Month> months = rruleParser.parseMonths("13");
    }

    @Test
    public void shouldParseIntDays() {
        List<Integer> actual = rruleParser.parseInt("1,2,3", -31, 31, false);
        List<Integer> expected = Arrays.asList(1, 2, 3);

        assertThat(actual, is(expected));
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDayLessThanMin() {
        String currentInt = "-35";
        int min = -31;
        int max = 31;
        try {
            List<Integer> days = rruleParser.parseInt(currentInt, min, max, false);
        } catch (IllegalArgumentException e) {
            String msg = currentInt + " is below minimum " + min;
            assertEquals(msg, e.getMessage());
            throw e;
        }
        fail("IllegalArgumentException not thrown when incorrect day");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDayGreaterThanMax() {
        String currentInt = "35";
        int min = -31;
        int max = 31;
        try {
            List<Integer> days = rruleParser.parseInt(currentInt, min, max, false);
        } catch (IllegalArgumentException e) {
            String msg = currentInt + " is above maximum " + max;
            assertEquals(msg, e.getMessage());
            throw e;
        }
        fail("IllegalArgumentException not thrown when incorrect day");
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDayIsZero() {
        String currentInt = "0";
        int min = -31;
        int max = 31;
        try {
            List<Integer> days = rruleParser.parseInt(currentInt, min, max, false);
        } catch (IllegalArgumentException e) {
            String msg = "zero is not valid";
            assertEquals(msg, e.getMessage());
            throw e;
        }
        fail("IllegalArgumentException not thrown when incorrect day");
    }

    @Test
    public void shouldConvertStringToFreq() {
        Frequency freq = rruleParser.strToFreq("daily");
        assertEquals(freq, Frequency.DAILY);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenIncorrectFreqString() {
        String freqText = "Yearly";
        try {
            Frequency freq = rruleParser.strToFreq(freqText);
        } catch (IllegalArgumentException e) {
            String msg = "Incorrect String for frequency" + freqText;
            assertEquals(msg, e.getMessage());
            throw e;
        }
        fail("NoSuchElementException not thrown when incorrect freq");
    }

    @Test
    public void shouldConvertShortDayToWeekday() {
        String shortName = "TH";
        DayOfWeek dayOfWeek = rruleParser.getByShortName(shortName);

        assertEquals(dayOfWeek, DayOfWeek.THURSDAY);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenInvalidShortDay() {
        String shortName = "TJ";
        try {
            DayOfWeek dayOfWeek = rruleParser.getByShortName(shortName);
        } catch (NoSuchElementException e) {
            String msg = "Incorrect Short name for Weekday" + shortName;
            assertEquals(msg, e.getMessage());
            throw e;
        }
        fail("NoSuchElementException not thrown when incorrect shortDay");
    }

    @Test
    public void shouldReturnQualifiedWeekdays() {
        String weekdays = "-2MO";
        List<QualifiedWeekday> list = rruleParser.parseQualifiedWeekdays(weekdays);

        assertEquals(-2, list.get(0).getOrdinal());
        assertEquals(DayOfWeek.MONDAY, list.get(0).getWeekday());
    }
}

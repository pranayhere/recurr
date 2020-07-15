package com.we.recurr.parser;

import com.we.recurr.domain.Frequency;
import com.we.recurr.domain.QualifiedWeekday;
import com.we.recurr.domain.RRule;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RRuleParser implements RuleParser {
    private String ruleText;
    private LocalDateTime dtStart;
    private static final Pattern ORDINAL_DAY_PATTERN = Pattern.compile("^([-+]?\\d)(\\w{2})$");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

    public RRuleParser() {}

    public RRuleParser(String ruleText, LocalDateTime dtStart) {
        this.ruleText = ruleText.toUpperCase().replace("RRULE:", "");
        this.dtStart = dtStart; 
    }

    @Override
    public RRule parse() {
        RRule rrule = new RRule();
        String[] components = this.ruleText.split(";");
        rrule.setDtStart(this.dtStart);

        for (String component : components) {
            String[] parts = component.split("=");

            if (parts.length < 2) {
                System.out.println("RRule segment is invalid" + component);
            }

            String directive = parts[0];
            String value = parts[1];

            switch (directive) {
                case "FREQ":
                    rrule.setFrequency(strToFreq(value.toLowerCase()));
                    break;
                case "UNTIL":
                    LocalDateTime until = LocalDateTime.parse(value, formatter);
                    rrule.setUntil(until);
                    break;
                case "COUNT":
                    rrule.setCount(Integer.parseInt(value));
                    break;
                case "DTSTART":
                    LocalDateTime dtStart = LocalDateTime.parse(value, formatter);
                    rrule.setDtStart(dtStart);
                    break;
                case "INTERVAL":
                    rrule.setInterval(Integer.parseInt(value));
                    break;
                case "BYDAY":
                    rrule.setByWeekdays(parseQualifiedWeekdays(value));
                    break;
                case "BYMONTHDAY":
                    rrule.setByMonthDays(parseInt(value, -31, 31, false));
                    break;
                case "BYMONTH":
                    rrule.setByMonths(parseMonths(value));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown dimension : " + directive);
            }
        }
        rrule.validate();
        return rrule;
    }

    public List<Month> parseMonths(String str) {
        String[] parts = str.split(",");
        List<Month> months = new ArrayList<>();

        for (String p: parts) {
            int monthNo = Integer.parseInt(p.trim());

            months.add(Month.of(monthNo));
        }

        return months;
    }

    public List<Integer> parseInt(String str, int min, int max, boolean allowZero) {
        List<Integer> ints = new ArrayList<>();
        String[] parts = str.split(",");

        for (String p: parts) {
            int currentInt = Integer.parseInt(p);

            if (currentInt == 0 && !allowZero) {
                throw new IllegalArgumentException("zero is not valid");
            }

            if (currentInt < min) {
                throw new IllegalArgumentException(currentInt + " is below minimum " + min);
            }

            if (currentInt > max) {
                throw new IllegalArgumentException(currentInt + " is above maximum " + max);
            }

            ints.add(currentInt);
        }
        return ints;
    }

    public List<QualifiedWeekday> parseQualifiedWeekdays(String str) {
        String[] parts = str.split(",");
        List<QualifiedWeekday> wds = new ArrayList<>();

        for (String p : parts) {
            if (p.length() == 0) {
                throw new IllegalArgumentException("cannot have empty weekday segment in a comma-separated list");
            }

            Matcher ordinalDay = ORDINAL_DAY_PATTERN.matcher(p);
            if (ordinalDay.find()) {
                int ordinal = Integer.parseInt(ordinalDay.group(1));
                DayOfWeek weekDay = getByShortName(ordinalDay.group(2));
                wds.add(new QualifiedWeekday(ordinal, weekDay));
            } else {
                DayOfWeek weekDay = getByShortName(p);
                wds.add(new QualifiedWeekday(0, weekDay)); // check this... may cause trouble
            }
        }

        return wds;
    }

    public DayOfWeek getByShortName(String shortName) {
        String abbrName = shortName.toUpperCase();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.name().substring(0, 2).equalsIgnoreCase(abbrName)) {
                return day;
            }
        }
        throw new NoSuchElementException("Incorrect Short name for Weekday" + shortName);
    }

    public Frequency strToFreq(String freq) {
        switch (freq) {
            case "secondly":
                return Frequency.SECONDLY;
            case "daily":
                return Frequency.DAILY;
            case "weekly":
                return Frequency.WEEKLY;
            case "monthly":
                return Frequency.MONTHLY;
            default:
                throw new NoSuchElementException("Incorrect String for frequency " + freq);
        }
    }
}

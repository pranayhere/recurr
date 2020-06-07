[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.pranayhere/recurr/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pranayhere/recurr)

# Recurr

Recurr is the library for generating recurring events. It follows RFC-5545 to generate recurring events. The project is still in active development. 

Current implementation supports recurring events of type
  - Daily
  - Weekly
  - Monthly

## Usage
```
<dependecy>
	<groupId>com.github.pranayhere</groupId>  
	<artifactId>recurr</artifactId>
	<version>1.1.0</version>
</dependency>
```

## Guide

The library extends Iterator and make use of `hasNext()` and `next()` to get the date for the upcoming events.
```
LocalDateTime today = LocalDateTime.of(2020, 5, 5, 9, 15, 20);
Iterator<LocalDateTime> itr = new RecurrenceIterator("RRULE:FREQ=MONTHLY;BYDAY=1TH;INTERVAL=2;COUNT=3", today);
while (itr.hasNext()) {  
    LocalDateTime date = itr.next();  
}
```

**Examples**
| RRule | Description |
|--|--|
| RRULE:FREQ=DAILY;COUNT=3 | Repeat Every day |
|RRULE:FREQ=DAILY;INTERVAL=2;COUNT=3 | Repeat Every Two days |
|RRULE:FREQ=WEEKLY;COUNT=3 | Repeat Every Week |
|RRULE:FREQ=WEEKLY;INTERVAL=2;COUNT=3 | Repeat Every Two Weeks |
|RRULE:FREQ=WEEKLY;BYDAY=MO,TH;COUNT=3 | Repeat On Monday And Thursday | 
|RRULE:FREQ=WEEKLY;BYDAY=SA,SU;COUNT=3 | Repeat on Every Weekend |
| RRULE:FREQ=MONTHLY;BYMONTHDAY=10;COUNT=3 | Repeat Every 10th of the Month |
|RRULE:FREQ=MONTHLY;BYMONTHDAY=-1,-31;COUNT=3 | Repeat on Last day of the month |
| RRULE:FREQ=MONTHLY;BYDAY=1FR;COUNT=3 | Repeat on first friday |
| RRULE:FREQ=MONTHLY;BYDAY=-1FR;COUNT=3 | Repeat on Last Friday |

## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fpranayhere%2Frecurr.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fpranayhere%2Frecurr?ref=badge_large)

## Contribution
Please feel free to contribute.

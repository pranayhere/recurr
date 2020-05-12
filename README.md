# Recurr

Recurr is the library for generating recurring events. It follows RFC-5545 to generate recurring events. The project is still in active development. 

Current implementation supports recurring events of type
  - Daily
  - Weekly
  - Monthly

# Usage
```
<dependecy>
	<groupId>com.github.pranayhere</groupId>  
	<artifactId>recurr</artifactId>
	<version>0.1.2-SNAPSHOT</version>
</dependency>
```

# Guide

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
|RRULE:FREQ=DAILY;INTERVAL=2 | Repeat Every Two days |
|RRULE:FREQ=WEEKLY; | Repeat Every Week |
|RRULE:FREQ=WEEKLY;INTERVAL=2 | Repeat Every Two Weeks |
|RRULE:FREQ=WEEKLY;BYDAY=MO,TH | Repeat On Monday And Thursday | 
|RRULE:FREQ=WEEKLY;BYDAY=SA,SU | Repeat on Every Weekend |
| RRULE:FREQ=MONTHLY;BYMONTHDAY=10 | Repeat Every 10th of the Month |
|RRULE:FREQ=MONTHLY;BYMONTHDAY=-1,-31 | Repeat on Last day of the month |
| RRULE:FREQ=MONTHLY;BYDAY=1FR | Repeat on first friday |
| RRULE:FREQ=MONTHLY;BYDAY=-1FR | Repeat on Last Friday |

# Contribution
Please feel free to contribute.
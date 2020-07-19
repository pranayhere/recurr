[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.pranayhere/recurr/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pranayhere/recurr) [![javadoc](https://javadoc.io/badge2/com.github.pranayhere/recurr/javadoc.svg)](https://javadoc.io/doc/com.github.pranayhere/recurr)
# Recurr

A simple and fast library to work with Recurring Events.

Current implementation supports recurring events of type
  - Daily
  - Weekly
  - Monthly

## Usage
```
<dependecy>
	<groupId>com.github.pranayhere</groupId>  
	<artifactId>recurr</artifactId>
	<version>1.1.5</version>
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
By contributing, you agree that your contributions will be licensed under its MIT License.

[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fpranayhere%2Frecurr.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fpranayhere%2Frecurr?ref=badge_large)

## Contributing
We love your input! We want to make contributing to this project as easy and transparent as possible.
Any contributions you make are greatly appreciated.

Fork the Project
Create your Feature Branch (git checkout -b feature/AmazingFeature)
Commit your Changes (git commit -m 'Add some AmazingFeature')
Push to the Branch (git push origin feature/AmazingFeature)
Open a Pull Request

## We Develop with Github
We use github to host code, to track issues and feature requests, as well as accept pull requests.

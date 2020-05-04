package com.we.recurr.expansions;

import java.time.LocalDateTime;
import java.util.List;

public interface Expandable {
    List<LocalDateTime> expand();
}

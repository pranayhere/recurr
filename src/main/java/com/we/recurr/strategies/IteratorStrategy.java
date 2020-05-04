package com.we.recurr.strategies;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public abstract class IteratorStrategy {
    public Queue<LocalDateTime> q;
    public int totalQueued;
    public int qCap;
    public LocalDateTime minTime;
    public LocalDateTime maxTime;
    public boolean pastMaxTime;

    public IteratorStrategy() {
        this.q = new ArrayDeque<>();
    }

    public abstract LocalDateTime next();
    public abstract boolean valid(LocalDateTime localDateTime);
    public abstract List<LocalDateTime> variations(LocalDateTime t);

    public LocalDateTime timeOrZero(LocalDateTime until) {
        if (until != null) {
            return until;
        }
        return LocalDateTime.MAX;
    }
}

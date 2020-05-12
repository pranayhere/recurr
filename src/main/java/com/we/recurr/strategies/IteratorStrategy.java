package com.we.recurr.strategies;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public abstract class IteratorStrategy {
    protected Queue<LocalDateTime> q;
    protected int totalQueued;
    protected int qCap;
    protected LocalDateTime minTime;
    protected LocalDateTime maxTime;
    protected boolean pastMaxTime;

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

    public Queue<LocalDateTime> getQueue() {
        return q;
    }

    public int getTotalQueued() {
        return totalQueued;
    }

    public void setTotalQueued(int totalQueued) {
        this.totalQueued = totalQueued;
    }

    public int getqCap() {
        return qCap;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }

    public boolean isPastMaxTime() {
        return pastMaxTime;
    }

    public void setPastMaxTime(boolean pastMaxTime) {
        this.pastMaxTime = pastMaxTime;
    }
}

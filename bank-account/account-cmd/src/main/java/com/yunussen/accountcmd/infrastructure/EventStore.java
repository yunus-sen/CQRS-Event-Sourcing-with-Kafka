package com.yunussen.accountcmd.infrastructure;

import com.yunussen.cqrscore.events.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);

    List<BaseEvent> getEvents(String aggregateId);
}

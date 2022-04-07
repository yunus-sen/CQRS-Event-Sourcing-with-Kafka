package com.yunussen.cqrscore.handlers;

import com.yunussen.cqrscore.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);

    T getById(String id);
}

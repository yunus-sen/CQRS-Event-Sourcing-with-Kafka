package com.yunussen.cqrscore.producer;

import com.yunussen.cqrscore.events.BaseEvent;

public interface EventProducer {
    void produce(String topicName, BaseEvent event);
}

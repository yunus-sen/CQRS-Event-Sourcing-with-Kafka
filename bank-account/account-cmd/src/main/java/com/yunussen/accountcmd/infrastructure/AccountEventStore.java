package com.yunussen.accountcmd.infrastructure;

import com.yunussen.accountcmd.domain.AccountAggregate;
import com.yunussen.accountcmd.domain.EventStoreRepository;
import com.yunussen.cqrscore.events.BaseEvent;
import com.yunussen.cqrscore.events.EventModel;
import com.yunussen.cqrscore.exception.AggregateNotFoundException;
import com.yunussen.cqrscore.exception.ConcurrencyException;
import com.yunussen.cqrscore.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEventStore implements EventStore {
    @Autowired
    private EventProducer eventProducer;
    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        var version = expectedVersion;

        for (var event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();

            var persistedEvent = eventStoreRepository.save(eventModel);
            if (!persistedEvent.getId().isBlank()) {
                //evetn send to kafka
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null && eventStream.isEmpty()) {
            throw new AggregateNotFoundException();
        }
        return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
    }
}

package com.yunussen.accountcmd.infrastructure;

import com.yunussen.cqrscore.events.BaseEvent;
import com.yunussen.cqrscore.producer.EventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class AccountEventProducer implements EventProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountEventProducer.class);

    @Override
    public void produce(String topicName, BaseEvent event) {
        try {
            kafkaTemplate.send(topicName, event);
        } catch (Exception e) {
            LOGGER.error("An error occurred while sending log", e);
        }
    }
}

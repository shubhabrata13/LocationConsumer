package com.apmm.listener;

import com.apmm.domain.Location;
import com.apmm.repository.LocationRepository;
import com.apmm.service.ProcessEventService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;


@Service
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;

    @Autowired
    private LocationRepository locationrepo;

    @Autowired
    private ProcessEventService processEventService;

    @EventListener(ApplicationStartedEvent.class)
    public Flux<Location> startKafkaConsumer() {
        return reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                .delayElements(Duration.ofSeconds(2L))
                .doOnNext(consumerRecord -> logger.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .flatMap(consumerRecord -> processEventService.processEvent(consumerRecord.value()))
                .doOnNext(employee -> logger.info("successfully consumed {}={}", employee, employee))
                .doOnError(throwable -> logger.error("something bad happened while consuming : {}", throwable.getMessage()));
    }

    public void setReactiveKafkaConsumerTemplate(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

    public void setLocationrepo(LocationRepository locationrepo) {
        this.locationrepo = locationrepo;
    }

    public ReactiveKafkaConsumerTemplate<String, String> getReactiveKafkaConsumerTemplate() {
        return reactiveKafkaConsumerTemplate;
    }

    public LocationRepository getLocationrepo() {
        return locationrepo;
    }

}

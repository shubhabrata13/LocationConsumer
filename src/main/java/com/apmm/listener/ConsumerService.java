package com.apmm.listener;

import com.apmm.domain.Location;
import com.apmm.repository.LocationRepository;
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


@Service
public class ConsumerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

	@Autowired
	private ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;

	@Autowired
	private LocationRepository locationrepo;



	/*public ConsumerService(ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate,LocationRepository locationrepo) {
		this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
		this.locationrepo= locationrepo;
	}*/


	@EventListener(ApplicationStartedEvent.class)
	public Flux<Location> startKafkaConsumer() {
		return reactiveKafkaConsumerTemplate
				.receiveAutoAck()
				// .delayElements(Duration.ofSeconds(2L)) // BACKPRESSURE
				.doOnNext(consumerRecord -> logger.info("received key={}, value={} from topic={}, offset={}",
						consumerRecord.key(),
						consumerRecord.value(),
						consumerRecord.topic(),
						consumerRecord.offset())
				)
				.map(consumerRecord-> mapObject(consumerRecord.value()))
				.flatMap(locationrepo::save)
				.doOnNext(employee -> logger.info("successfully consumed {}={}", employee, employee))
				.doOnError(throwable -> logger.error("something bad happened while consuming : {}", throwable.getMessage()));
	}
	
	/*@KafkaListener(topics = {"POC.vesselschedule.topic.internal.any.v1"}, groupId = "spring-boot-kafka")
	  public void consume(ConsumerRecord<String, String> record) {
		logger.debug("received = " + record.value() + " with key " + record.key());
		VesselDetails vds= new VesselDetails(record.key(), record.value());
		vd.createVesselItems(vds);
		System.out.println("received = " + record.value() + " with key " + record.key());
	  }*/

	public Location mapObject(String message) {
		Location readValue=null;
		JsonNode node=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			node = mapper.readTree(message);
			readValue = new Location();
			readValue.setData(Json.of(message));
			//readValue = mapper.readValue(message, Location.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return readValue;
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

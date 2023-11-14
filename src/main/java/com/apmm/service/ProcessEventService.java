package com.apmm.service;

import com.apmm.domain.Location;
import com.apmm.repository.LocationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProcessEventService {
    @Autowired
    private LocationRepository locationrepo;

    public Mono<Location> processEvent(String message) {
        return this.locationrepo.save(mapObject(message));
    }

    public Location mapObject(String message) {
        Location readValue = null;
        JsonNode node = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readTree(message);
            readValue = new Location();
            readValue.setData(Json.of(message));
            //readValue = mapper.readValue(message, Location.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readValue;
    }
}

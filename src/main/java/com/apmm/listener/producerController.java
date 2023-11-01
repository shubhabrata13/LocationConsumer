/*
package com.apmm.listener;

import com.apmm.domain.Location;
import com.apmm.repository.LocationRepository;
import com.apmm.repository.LocationRepositoryNormal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kafka")
public final class producerController {

    @Autowired
    private ProducerService producerService;
	

    @GetMapping(path = "/health")
    public String health() {
        return "hello";

    }

    @PostMapping(value = "/publish")
    public ResponseEntity<Mono<String>> sendMessageToKafkaTopic() {
        try {
            producerService.saveMessage();

        } catch (Exception e) {
            e.printStackTrace();
        }

	return null;
    }
}*/

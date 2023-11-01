package com.apmm.repository;

import com.apmm.domain.Location;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface LocationRepository extends R2dbcRepository<Location, Integer>{

    /*@Query(value = "INSERT INTO location (data) VALUES ( :"+"'"+"message"+"'"+")", nativeQuery = true)
    Flux<Location> findByData(String message);*/

}

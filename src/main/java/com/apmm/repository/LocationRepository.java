package com.apmm.repository;

import com.apmm.domain.Location;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends R2dbcRepository<Location, Integer>{

}

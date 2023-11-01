package com.apmm.repository;

import com.apmm.domain.Location;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepositoryNormal extends CrudRepository<Location, String> {

}

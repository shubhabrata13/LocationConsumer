package com.apmm.LocationConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.apmm","com.apmm.repository"})
@EnableR2dbcRepositories(basePackages = {"com.apmm.repository"})
public class LocationConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationConsumerApplication.class, args);
	}

}

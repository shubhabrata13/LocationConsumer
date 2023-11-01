package com.apmm.domain;

import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import io.r2dbc.postgresql.codec.Json;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Type;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Entity(name = "location")
@Table
public class Location {
    //@Id
    //private String id;
    //private JsonNode data;

    @Type(JsonType.class)
    private Json data;

    public Location() {
        super();
    }
    public Location(Json data) {
        super();
        this.data = data;

    }

    public Json getData() {
        return data;
    }

    public void setData(Json data) {
        this.data = data;
    }

}

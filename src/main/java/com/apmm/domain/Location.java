package com.apmm.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import io.r2dbc.postgresql.codec.Json;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Type;
import org.springframework.data.relational.core.mapping.Table;

@Entity(name = "location")
@Table
public class Location {

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

package com.apmm.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Type;
import org.springframework.data.relational.core.mapping.Table;

@Entity(name = "location")
@Table
public class Location_1 {
    //@Id
    //private String id;
    //private JsonNode data;

    @Type(JsonType.class)
    private String data;

    public Location_1() {
        super();
    }
    public Location_1(String data) {
        super();
        this.data = data;

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}

package com.apmm.config;

import jakarta.validation.constraints.NotNull;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*@Configuration()
@ConfigurationProperties("spring.datasource")*/
public class LiquibaseConfig {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Bean("LiquibaseConfig")
    public Liquibase liquibase() throws SQLException, LiquibaseException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
     try  ( Liquibase liquibase = new Liquibase("changelog/db.changelog-master.yaml", new ClassLoaderResourceAccessor(), database)) {
         liquibase.update(new Contexts(), new LabelExpression());
         return liquibase;
     }
    }
}

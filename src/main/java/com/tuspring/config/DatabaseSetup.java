package com.tuspring.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSetup {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseSetup(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTables() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS FileShare (" +
                "id BIGSERIAL PRIMARY KEY, " +
                "file_name VARCHAR(255), " +
                "file_size BIGINT, " +
                "upload_time TIMESTAMP DEFAULT NOW(), " +
                "file_data BYTEA)");
    }

    public void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS FileShare");
    }
}

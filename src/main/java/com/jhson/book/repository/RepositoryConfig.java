package com.jhson.book.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {
    private final DataSource dataSource;

    public RepositoryConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BookRepository bookRepository() {
        //return new OracleBookRepository(dataSource);
        return new OracleTemplateBookRepository(dataSource);
    }

}

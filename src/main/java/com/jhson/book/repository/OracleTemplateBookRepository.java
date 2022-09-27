package com.jhson.book.repository;

import com.jhson.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OracleTemplateBookRepository implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public OracleTemplateBookRepository(@Autowired DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Book save(Book book) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("book").usingColumns("title", "content");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", book.getTitle());
        parameters.put("content", book.getContent());
        jdbcInsert.execute(parameters);
        return book;
    }

    @Override
    public List<Book> finaAll() {
        return jdbcTemplate.query("select * from book", bookRowMapper());
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        List<Book> results = jdbcTemplate.query("select * from book where title=?", bookRowMapper(), title);
        return results.stream().findAny();
    }

    private RowMapper<Book> bookRowMapper() {
        return (rs, rowNum) -> new Book(rs.getString("title"), rs.getString("content"));
    }
}

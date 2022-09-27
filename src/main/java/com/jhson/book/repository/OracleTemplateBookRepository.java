package com.jhson.book.repository;

import com.jhson.book.domain.Book;

import java.util.List;
import java.util.Optional;

public class OracleTemplateBookRepository implements BookRepository {

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public List<Book> finaAll() {
        return null;
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return Optional.empty();
    }
}

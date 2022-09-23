package com.jhson.book.repository;

import com.jhson.book.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);
    List<Book> finaAll();
    Optional<Book> findByTitle(String title);

}

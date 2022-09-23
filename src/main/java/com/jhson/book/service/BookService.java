package com.jhson.book.service;

import com.jhson.book.domain.Book;
import com.jhson.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 책 저장
     */
    public String create(Book book) {
        TestDuplicate(book);
        bookRepository.save(book);
        return book.getId();
    }

    private void TestDuplicate(Book book) {
        bookRepository.findByTitle(book.getTitle()).ifPresent(
                m->{
                    throw new IllegalStateException("Already exist");
                });

    }

    public List<Book> findBooks() {
        return bookRepository.finaAll();
    }
}

package com.jhson.book.controller;

import com.jhson.book.domain.Book;
import com.jhson.book.repository.BookRepository;
import com.jhson.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    @Autowired
    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping(value="/create")
    public String create() {
        return "book/create";
    }

    @PostMapping(value = "/create")
    public String create(Book book) {
        bookService.create(book);
        return "redirect:/";
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

}

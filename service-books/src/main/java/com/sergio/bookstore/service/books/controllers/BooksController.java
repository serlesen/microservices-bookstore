package com.sergio.bookstore.service.books.controllers;

import javax.servlet.http.HttpServletRequest;

import com.sergio.bookstore.service.books.dto.BookDto;
import com.sergio.bookstore.service.books.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BooksController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getOneBook(@PathVariable long bookId, HttpServletRequest request) {
        log.info("Looking for book {}", bookId);
        return ResponseEntity.ok(bookService.getBook(bookId));
    }
}

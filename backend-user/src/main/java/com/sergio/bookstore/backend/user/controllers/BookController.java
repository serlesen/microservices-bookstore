package com.sergio.bookstore.backend.user.controllers;

import com.sergio.bookstore.backend.user.dto.PriceDto;
import com.sergio.bookstore.backend.user.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<PriceDto> getOneBook(@PathVariable long bookId) {
        log.info("Looking for book {}", bookId);
        return ResponseEntity.ok(bookService.getBookWithPrice(bookId));
    }
}

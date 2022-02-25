package com.sergio.bookstore.service.books.services;

import com.sergio.bookstore.service.books.dto.BookDto;
import com.sergio.bookstore.service.books.exceptions.AppException;
import com.sergio.bookstore.service.books.mappers.BookMapper;
import com.sergio.bookstore.service.books.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final PriceService priceService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public BookDto getBook(long bookId) {
        BookDto bookDto = bookRepository.findById(bookId)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new AppException("No book found with ID " + bookId, HttpStatus.NOT_FOUND));

        BigDecimal price = circuitBreakerFactory.create("getPrice").run(() -> priceService.getPrice(bookId, bookDto),
                t -> BigDecimal.ZERO);
        bookDto.setPrice(price);

        return bookDto;
    }
}

package com.sergio.bookstore.backend.user.services;

import com.sergio.bookstore.backend.user.dto.BookDto;
import com.sergio.bookstore.backend.user.dto.PriceDto;
import com.sergio.bookstore.backend.user.feign.ServiceBooks;
import com.sergio.bookstore.backend.user.feign.ServicePrices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final ServiceBooks serviceBooks;
    private final ServicePrices servicePrices;

    public PriceDto getBookWithPrice(long bookId) {

        BookDto book = serviceBooks.getBook(bookId);

        PriceDto price = servicePrices.getPrice(bookId);

        price.setBook(book);

        return price;
    }
}

package com.sergio.bookstore.backend.user.services;

import java.io.IOException;

import com.sergio.bookstore.backend.user.dto.BookDto;
import com.sergio.bookstore.backend.user.dto.PriceDto;
import com.sergio.bookstore.backend.user.exceptions.AppException;
import com.sergio.bookstore.backend.user.retrofit.ServiceBooks;
import com.sergio.bookstore.backend.user.retrofit.ServicePrices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {

    private final ServiceBooks serviceBooks;
    private final ServicePrices servicePrices;

    public PriceDto getBookWithPrice(long bookId) {

        BookDto book;
        try {
            Response<BookDto> bookResponse = serviceBooks.getBook(bookId).execute();
            book = bookResponse.body();
        } catch (IOException e) {
            log.error("Unable to request service-books for book {}", e);
            throw new AppException("Unable to request service-books", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PriceDto price;
        try {
            Response<PriceDto> priceResponse = servicePrices.getPrice(bookId).execute();
            price = priceResponse.body();
        } catch (IOException e) {
            log.error("Unable to request service-prices for book {}", e);
            throw new AppException("Unable to request service-prices", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        price.setBook(book);
        return price;
    }
}

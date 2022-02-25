package com.sergio.bookstore.service.books.services;

import com.sergio.bookstore.service.books.dto.BookDto;
import com.sergio.bookstore.service.books.feign.ServicePrices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PriceService {

    private final ServicePrices servicePrices;

    public BigDecimal getPrice(long bookId, BookDto bookDto) {
        var price = servicePrices.getBookPrice(bookId);
        return price.getPrice();
    }

}

package com.sergio.bookstore.service.books.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sergio.bookstore.service.books.dto.BookDto;
import com.sergio.bookstore.service.books.feign.ServicePrices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PriceService {

    private final ServicePrices servicePrices;

    @HystrixCommand(fallbackMethod = "noPrice",
            commandProperties = {
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "25"),
            }
    )
    public BigDecimal getPrice(long bookId, BookDto bookDto) {
        var price = servicePrices.getBookPrice(bookId);
        return price.getPrice();
    }

    private BigDecimal noPrice(long bookId, BookDto bookDto) {
        return BigDecimal.ZERO;
    }

}

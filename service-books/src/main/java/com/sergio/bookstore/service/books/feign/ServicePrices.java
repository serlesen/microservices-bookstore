package com.sergio.bookstore.service.books.feign;

import com.sergio.bookstore.service.books.dto.PriceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-prices")
public interface ServicePrices {

    @RequestMapping("/prices/books/{bookId}")
    PriceDto getBookPrice(@PathVariable("bookId") long bookId);
}

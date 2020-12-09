package com.sergio.bookstore.backend.user.feign;

import com.sergio.bookstore.backend.user.dto.PriceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-prices")
public interface ServicePrices {

    @RequestMapping("/prices/books/{bookId}")
    PriceDto getPrice(@PathVariable("bookId") long bookId);
}

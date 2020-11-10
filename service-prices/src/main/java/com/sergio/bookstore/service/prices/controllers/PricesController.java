package com.sergio.bookstore.service.prices.controllers;

import com.sergio.bookstore.service.prices.dto.PriceDto;
import com.sergio.bookstore.service.prices.services.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PricesController {

    private final PriceService priceService;

    @GetMapping("/books/{bookId}")
    public ResponseEntity<PriceDto> getPrice(@PathVariable long bookId) {
        log.info("Looking for price of book {}", bookId);
        return ResponseEntity.ok(priceService.getPrice(bookId));
    }
}

package com.sergio.bookstore.service.orders.controllers;

import com.sergio.bookstore.service.orders.dto.OrderDto;
import com.sergio.bookstore.service.orders.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long orderId) {
        log.info("Looking for order {}", orderId);
        return ResponseEntity.ok(orderService.findOrder(orderId));
    }
}

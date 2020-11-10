package com.sergio.bookstore.backend.user.services;

import com.sergio.bookstore.backend.user.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    public OrderDto getOrder(long bookId) {
        // TODO call service-book to obtain the book information
        return null;
    }
}

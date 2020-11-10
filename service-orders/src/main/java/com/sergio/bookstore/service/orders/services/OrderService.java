package com.sergio.bookstore.service.orders.services;

import com.sergio.bookstore.service.orders.dto.OrderDto;
import com.sergio.bookstore.service.orders.exceptions.AppException;
import com.sergio.bookstore.service.orders.mappers.OrderMapper;
import com.sergio.bookstore.service.orders.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderDto findOrder(long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderDto)
                .orElseThrow(() -> new AppException("No order found with Id " + orderId, HttpStatus.NOT_FOUND));
    }
}

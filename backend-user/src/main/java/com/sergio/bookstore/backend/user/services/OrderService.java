package com.sergio.bookstore.backend.user.services;

import java.io.IOException;

import com.sergio.bookstore.backend.user.dto.BookDto;
import com.sergio.bookstore.backend.user.dto.OrderDto;
import com.sergio.bookstore.backend.user.exceptions.AppException;
import com.sergio.bookstore.backend.user.retrofit.ServiceBooks;
import com.sergio.bookstore.backend.user.retrofit.ServiceOrders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {

    private final ServiceBooks serviceBooks;
    private final ServiceOrders serviceOrders;

    public OrderDto getOrder(long orderId) {

        OrderDto order;
        try {
            Response<OrderDto> orderResponse = serviceOrders.getOrder(orderId).execute();
            order = orderResponse.body();
        } catch (IOException e) {
            log.error("Unable to request service-orders for order {}", orderId, e);
            throw new AppException("Unable to request service-orders", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BookDto book;
        try {
            Response<BookDto> bookResponse = serviceBooks.getBook(order.getBookId()).execute();
            book = bookResponse.body();
        } catch (IOException e) {
            log.error("Unable to request service-orders for order {}", orderId, e);
            throw new AppException("Unable to request service-books", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        order.setBook(book);

        return order;
    }
}

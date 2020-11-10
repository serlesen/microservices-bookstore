package com.sergio.bookstore.service.orders.mappers;

import com.sergio.bookstore.service.orders.dto.OrderDto;
import com.sergio.bookstore.service.orders.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toOrderDto(Order order);
}

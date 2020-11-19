package com.sergio.bookstore.backend.user.retrofit;

import com.sergio.bookstore.backend.user.dto.OrderDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceOrders {

    @GET("/orders/{orderId}")
    Call<OrderDto> getOrder(@Path("orderId") long orderId);
}

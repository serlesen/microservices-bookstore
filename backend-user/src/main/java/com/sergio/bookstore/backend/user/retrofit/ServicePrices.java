package com.sergio.bookstore.backend.user.retrofit;

import com.sergio.bookstore.backend.user.dto.PriceDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServicePrices {

    @GET("/prices/books/{bookId}")
    Call<PriceDto> getPrice(@Path("bookId") long bookId);
}

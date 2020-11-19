package com.sergio.bookstore.backend.user.config;

import com.sergio.bookstore.backend.user.retrofit.ServiceBooks;
import com.sergio.bookstore.backend.user.retrofit.ServiceOrders;
import com.sergio.bookstore.backend.user.retrofit.ServicePrices;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    private final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    @Bean
    public ServiceBooks buildServiceBooksEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://k8s-service-books.default.svc.cluster.local/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        return retrofit.create(ServiceBooks.class);
    }

    @Bean
    public ServiceOrders buildServiceOrdersEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://k8s-service-orders.default.svc.cluster.local/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        return retrofit.create(ServiceOrders.class);
    }

    @Bean
    public ServicePrices buildServicePricesEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://k8s-service-prices.default.svc.cluster.local/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        return retrofit.create(ServicePrices.class);
    }
}

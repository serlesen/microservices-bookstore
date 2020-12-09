package com.sergio.bookstore.backend.user.feign;

import com.sergio.bookstore.backend.user.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-books")
public interface ServiceBooks {

    @RequestMapping("/books/{bookId}")
    BookDto getBook(@PathVariable("bookId") long bookId);

}

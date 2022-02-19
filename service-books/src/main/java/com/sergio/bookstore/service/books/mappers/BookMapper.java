package com.sergio.bookstore.service.books.mappers;

import com.sergio.bookstore.service.books.dto.BookDto;
import com.sergio.bookstore.service.books.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toBookDto(Book book);
}

package com.sergio.bookstore.service.books.mappers;

import com.sergio.bookstore.service.books.dto.AuthorDto;
import com.sergio.bookstore.service.books.entities.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toAuthorDto(Author author);
}

package com.sergio.bookstore.service.user.mappers;

import com.sergio.bookstore.service.user.dto.CredentialsDto;
import com.sergio.bookstore.service.user.dto.UserCreationDto;
import com.sergio.bookstore.service.user.dto.UserDto;
import com.sergio.bookstore.service.user.entities.BookstoreUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.login", target = "login")
    @Mapping(source = "token", target = "token")
    UserDto toUserDto(BookstoreUser user, String token);

    @Mapping(source = "encodedPassword", target = "password")
    BookstoreUser toBookstoreUser(UserCreationDto userDto, String encodedPassword);
}

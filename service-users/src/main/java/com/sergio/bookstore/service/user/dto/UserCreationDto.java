package com.sergio.bookstore.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCreationDto {

    private String login;
    private char[] password;
    private LocalDate birthDate;
}

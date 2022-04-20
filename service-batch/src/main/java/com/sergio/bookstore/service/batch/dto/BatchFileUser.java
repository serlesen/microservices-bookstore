package com.sergio.bookstore.service.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BatchFileUser {

    private Long id;
    private String name;
    private String email;
    private String birthDate;
}

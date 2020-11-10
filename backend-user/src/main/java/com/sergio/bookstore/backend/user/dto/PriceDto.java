package com.sergio.bookstore.backend.user.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PriceDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long bookId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BookDto book;

    private BigDecimal price;
}

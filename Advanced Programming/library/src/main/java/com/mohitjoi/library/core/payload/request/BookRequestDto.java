package com.mohitjoi.library.core.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRequestDto {

    private String title;

    private String author;

    private String isbn;

    private Integer totalCopies;

}

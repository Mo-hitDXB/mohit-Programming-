package com.mohitjoi.library.core.payload.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookResponseDto {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int issuedCopies;
}

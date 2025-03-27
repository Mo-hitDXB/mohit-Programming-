package com.mohitjoi.library.core.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookUpdateDto {

    private String title;

    private String author;

    private String isbn;

    private Integer totalCopies;

}

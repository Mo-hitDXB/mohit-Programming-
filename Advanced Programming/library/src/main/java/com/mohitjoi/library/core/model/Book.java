package com.mohitjoi.library.core.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;
    private String author;

    private String isbn;

    private int totalCopies;
    private int issuedCopies;

    private boolean available;
}

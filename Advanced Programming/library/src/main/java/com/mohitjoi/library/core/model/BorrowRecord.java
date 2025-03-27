package com.mohitjoi.library.core.model;

import com.mohitjoi.library.core.type.BorrowStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document(collection = "borrow_records")
public class BorrowRecord {

    @Id
    private String id;

    private String userId;
    private String bookId;

    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    private BorrowStatus status;
}

package com.mohitjoi.library.core.payload.response;

import com.mohitjoi.library.core.type.BorrowStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BorrowResponseDto {
    private String id;
    private String userId;
    private String bookId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private BorrowStatus status;
}

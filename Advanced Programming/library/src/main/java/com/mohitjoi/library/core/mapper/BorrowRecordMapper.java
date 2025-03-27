package com.mohitjoi.library.core.mapper;

import com.mohitjoi.library.core.model.BorrowRecord;
import com.mohitjoi.library.core.payload.request.BorrowRequestDto;
import com.mohitjoi.library.core.payload.response.BorrowResponseDto;
import com.mohitjoi.library.core.type.BorrowStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BorrowRecordMapper {

    public BorrowRecord borrowRecordRequestDtoToBorrowRecord(BorrowRequestDto dto) {
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUserId(dto.getUserId());
        borrowRecord.setBookId(dto.getBookId());
        borrowRecord.setBorrowDate(LocalDateTime.now());
        borrowRecord.setStatus(BorrowStatus.BORROWED);
        return borrowRecord;
    }

    public BorrowResponseDto borrowRecordToBorrowResponseDto(BorrowRecord borrowRecord) {
        BorrowResponseDto dto = new BorrowResponseDto();
        dto.setId(borrowRecord.getId());
        dto.setUserId(borrowRecord.getUserId());
        dto.setBookId(borrowRecord.getBookId());
        dto.setBorrowDate(borrowRecord.getBorrowDate());
        dto.setReturnDate(borrowRecord.getReturnDate());
        dto.setStatus(borrowRecord.getStatus());
        return dto;
    }
}

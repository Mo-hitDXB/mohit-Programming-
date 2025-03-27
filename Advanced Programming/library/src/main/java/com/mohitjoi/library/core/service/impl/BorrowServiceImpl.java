package com.mohitjoi.library.core.service.impl;

import com.mohitjoi.library.core.exception.ModuleException;
import com.mohitjoi.library.core.mapper.BorrowRecordMapper;
import com.mohitjoi.library.core.model.Book;
import com.mohitjoi.library.core.model.BorrowRecord;
import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import com.mohitjoi.library.core.payload.request.BorrowRequestDto;
import com.mohitjoi.library.core.payload.response.BorrowResponseDto;
import com.mohitjoi.library.core.repository.BookRepository;
import com.mohitjoi.library.core.repository.BorrowRecordRepository;
import com.mohitjoi.library.core.service.BorrowService;
import com.mohitjoi.library.core.type.BorrowStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    @NonNull
    private final BorrowRecordRepository borrowRecordRepository;

    @NonNull
    private final BookRepository bookRepository;

    @NonNull
    private final BorrowRecordMapper borrowRecordMapper;

    @Override
    public ResponseEntityDto borrowBook(BorrowRequestDto request) {
        Optional<Book> optionalBook = bookRepository.findById(request.getBookId());
        if (optionalBook.isEmpty()) {
            throw new ModuleException("Book not found!");
        }

        Book book = optionalBook.get();
        if (book.getIssuedCopies() >= book.getTotalCopies()) {
            throw new ModuleException("No available copies to borrow.");
        }

        book.setIssuedCopies(book.getIssuedCopies() + 1);
        bookRepository.save(book);

        BorrowRecord borrowRecord = borrowRecordMapper.borrowRecordRequestDtoToBorrowRecord(request);
        BorrowRecord savedBorrowRecord = borrowRecordRepository.save(borrowRecord);

        BorrowResponseDto response = borrowRecordMapper.borrowRecordToBorrowResponseDto(savedBorrowRecord);
        return new ResponseEntityDto(false, response);
    }

    @Override
    public ResponseEntityDto handOverBook(String borrowId) {
        Optional<BorrowRecord> optionalBorrowRecord = borrowRecordRepository.findById(borrowId);
        if (optionalBorrowRecord.isEmpty()) {
            throw new ModuleException("Borrow record not found!");
        }

        BorrowRecord borrowRecord = optionalBorrowRecord.get();
        if (borrowRecord.getStatus() == BorrowStatus.RETURNED) {
            throw new ModuleException("This book has already been returned.");
        }

        Optional<Book> optionalBook = bookRepository.findById(borrowRecord.getBookId());
        if (optionalBook.isEmpty()) {
            throw new ModuleException("Associated book not found!");
        }

        Book book = optionalBook.get();
        book.setIssuedCopies(Math.max(book.getIssuedCopies() - 1, 0));
        bookRepository.save(book);

        borrowRecord.setStatus(BorrowStatus.RETURNED);
        borrowRecord.setReturnDate(LocalDateTime.now());

        BorrowRecord updatedBorrowRecord = borrowRecordRepository.save(borrowRecord);
        BorrowResponseDto response = borrowRecordMapper.borrowRecordToBorrowResponseDto(updatedBorrowRecord);

        return new ResponseEntityDto(false, response);
    }

}

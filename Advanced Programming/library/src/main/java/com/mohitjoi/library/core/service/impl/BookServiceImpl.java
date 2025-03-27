package com.mohitjoi.library.core.service.impl;

import com.mohitjoi.library.core.exception.ModuleException;
import com.mohitjoi.library.core.mapper.BookMapper;
import com.mohitjoi.library.core.model.Book;
import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import com.mohitjoi.library.core.payload.request.BookRequestDto;
import com.mohitjoi.library.core.payload.request.BookUpdateDto;
import com.mohitjoi.library.core.payload.response.BookResponseDto;
import com.mohitjoi.library.core.repository.BookRepository;
import com.mohitjoi.library.core.repository.BorrowRecordRepository;
import com.mohitjoi.library.core.service.BookService;
import com.mohitjoi.library.core.type.BorrowStatus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @NonNull
    private final BookRepository bookRepository;

    @NonNull
    private final BookMapper bookMapper;

    @NonNull
    private final BorrowRecordRepository borrowRecordRepository;

    @Override
    public ResponseEntityDto createBook(BookRequestDto bookRequestDto) {
        Book book = bookMapper.bookRequestToBook(bookRequestDto);
        Book savedBook = bookRepository.save(book);
        BookResponseDto responseDto = bookMapper.bookToBookResponse(savedBook);

        return new ResponseEntityDto(false, responseDto);
    }

    @Override
    public ResponseEntityDto getBookById(String id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new ModuleException("Book not found!");
        }

        BookResponseDto responseDto = bookMapper.bookToBookResponse(bookOpt.get());
        return new ResponseEntityDto(false, responseDto);
    }

    @Override
    public ResponseEntityDto getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponseDto> responseList = books.stream().map(bookMapper::bookToBookResponse).collect(Collectors.toList());

        return new ResponseEntityDto(false, responseList);
    }

    @Override
    public ResponseEntityDto updateBook(String id, BookUpdateDto bookRequestDto) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new ModuleException("Book not found!");
        }

        Book book = bookOpt.get();
        bookMapper.updateBookFromRequest(book, bookRequestDto);
        Book updated = bookRepository.save(book);
        BookResponseDto responseDto = bookMapper.bookToBookResponse(updated);

        return new ResponseEntityDto(false, responseDto);
    }

    @Override
    public ResponseEntityDto deleteBook(String id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new ModuleException("Book not found!");
        }

        boolean isBookBorrowed = borrowRecordRepository.existsByBookIdAndStatus(id, BorrowStatus.BORROWED);
        if (isBookBorrowed) {
            throw new ModuleException("Cannot delete book: it is currently borrowed by a user.");
        }

        bookRepository.deleteById(id);
        return new ResponseEntityDto(false, "Book deleted!");
    }

}

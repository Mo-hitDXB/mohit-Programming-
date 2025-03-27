package com.mohitjoi.library.core.mapper;

import com.mohitjoi.library.core.model.Book;
import com.mohitjoi.library.core.payload.request.BookRequestDto;
import com.mohitjoi.library.core.payload.request.BookUpdateDto;
import com.mohitjoi.library.core.payload.response.BookResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book bookRequestToBook(BookRequestDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setTotalCopies(dto.getTotalCopies());
        book.setIssuedCopies(0);
        book.setAvailable(true);
        return book;
    }

    public void updateBookFromRequest(Book book, BookUpdateDto dto) {
        if (dto.getTitle() != null) {
            book.setTitle(dto.getTitle());
        }
        if (dto.getAuthor() != null) {
            book.setAuthor(dto.getAuthor());
        }
        if (dto.getIsbn() != null) {
            book.setIsbn(dto.getIsbn());
        }
        if (dto.getTotalCopies() != null) {
            book.setTotalCopies(dto.getTotalCopies());
        }
    }

    public BookResponseDto bookToBookResponse(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setTotalCopies(book.getTotalCopies());
        dto.setIssuedCopies(book.getIssuedCopies());
        return dto;
    }
}

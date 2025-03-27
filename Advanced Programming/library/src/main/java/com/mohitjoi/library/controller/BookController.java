package com.mohitjoi.library.controller;

import com.mohitjoi.library.core.payload.request.BookRequestDto;
import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import com.mohitjoi.library.core.payload.request.BookUpdateDto;
import com.mohitjoi.library.core.service.BookService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    @NonNull
    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseEntityDto> addBook(@RequestBody BookRequestDto bookRequestDto) {
        ResponseEntityDto response = bookService.createBook(bookRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntityDto> getBook(@PathVariable String id) {
        ResponseEntityDto response = bookService.getBookById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ResponseEntityDto> getAllBooks() {
        ResponseEntityDto response = bookService.getAllBooks();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseEntityDto> updateBook(@PathVariable String id, @RequestBody BookUpdateDto bookUpdateDto) {
        ResponseEntityDto response = bookService.updateBook(id, bookUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseEntityDto> deleteBook(@PathVariable String id) {
        ResponseEntityDto response = bookService.deleteBook(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

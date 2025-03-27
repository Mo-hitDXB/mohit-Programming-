package com.mohitjoi.library.core.service;

import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import com.mohitjoi.library.core.payload.request.BookRequestDto;
import com.mohitjoi.library.core.payload.request.BookUpdateDto;

public interface BookService {

    ResponseEntityDto createBook(BookRequestDto bookRequestDto);

    ResponseEntityDto getBookById(String id);

    ResponseEntityDto getAllBooks();

    ResponseEntityDto updateBook(String id, BookUpdateDto bookRequestDto);

    ResponseEntityDto deleteBook(String id);

}

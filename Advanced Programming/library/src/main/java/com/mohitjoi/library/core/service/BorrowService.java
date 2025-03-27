package com.mohitjoi.library.core.service;

import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import com.mohitjoi.library.core.payload.request.BorrowRequestDto;

public interface BorrowService {

    ResponseEntityDto borrowBook(BorrowRequestDto request);

    ResponseEntityDto handOverBook(String borrowId);
}

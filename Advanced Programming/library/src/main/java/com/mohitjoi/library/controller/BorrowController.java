package com.mohitjoi.library.controller;

import com.mohitjoi.library.core.payload.common.ResponseEntityDto;
import com.mohitjoi.library.core.payload.request.BorrowRequestDto;
import com.mohitjoi.library.core.service.BorrowService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrow")
@RequiredArgsConstructor
public class BorrowController {

    @NonNull
    private final BorrowService borrowService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ResponseEntityDto> borrowBook(@RequestBody BorrowRequestDto request) {
        ResponseEntityDto response = borrowService.borrowBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/handover/{borrowId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseEntityDto> handOverBook(@PathVariable String borrowId) {
        ResponseEntityDto response = borrowService.handOverBook(borrowId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

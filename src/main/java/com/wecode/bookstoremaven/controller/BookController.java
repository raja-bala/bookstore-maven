package com.wecode.bookstoremaven.controller;

import com.wecode.bookstoremaven.dto.BookDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class BookController {
    @GetMapping("books")
    public ResponseEntity<List<BookDto>> getBooks() {
        BookDto bookDto1 = BookDto.builder()
                .title("My first Book-changed")
                .build();
        BookDto bookDto2 = BookDto.builder()
                .title("My second Book-changed")
                .build();
        List<BookDto> bookDtoList = new ArrayList<>();

        bookDtoList.add(bookDto1);
        bookDtoList.add(bookDto2);

        return ResponseEntity.ok(bookDtoList);

    }
}

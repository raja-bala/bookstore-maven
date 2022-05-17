package com.wecode.bookstoremaven.controller;

import com.wecode.bookstoremaven.dto.BookDto;
import com.wecode.bookstoremaven.model.Book;
import com.wecode.bookstoremaven.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeEach
    void setMockOutput() {



    }
    private BookDto getBookDto() {
        Random rnd = new Random();
        return BookDto.builder()
                .title("test title")
                .description("test description")
                .id(rnd.nextLong())
                .releaseYear(2020)
                .build();
    }
    @Test
    void shouldReturnBookDtoListWhenGetBooksCalled() {
        List<BookDto> bookDtos = new ArrayList<>();
        BookDto bookDto = getBookDto();
        bookDtos.add(bookDto);
        when(bookService.getBooks()).thenReturn(bookDtos);

        ResponseEntity<List<BookDto>> books = bookController.getBooks();
        assertThat(books.getBody()).isNotNull();
        assertThat(books.getBody().size()).isEqualTo(1);

    }
    @Test
    void shouldReturnBookDtoListWhenGetBooksByTitleCalled() {
        List<BookDto> bookDtos = new ArrayList<>();
        BookDto bookDto = getBookDto();
        bookDtos.add(bookDto);
        when(bookService.getBooksByTitle(anyString())).thenReturn(bookDtos);

        ResponseEntity<List<BookDto>> books = bookController.getBooksByTitle("test title");
        assertThat(books.getBody()).isNotNull();
        assertThat(books.getBody().size()).isEqualTo(1);

    }
}
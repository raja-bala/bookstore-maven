package com.wecode.bookstoremaven.service;

import com.wecode.bookstoremaven.dto.BookDto;
import com.wecode.bookstoremaven.model.Book;
import com.wecode.bookstoremaven.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setMockOutput() {
        List<Book> books = new ArrayList<>();
        Book book = getBook();
        books.add(book);
        when(bookRepository.findAll()).thenReturn(books);
        BookDto bookDto = getBookDto();
        when(mapper.map(book,BookDto.class)).thenReturn(bookDto);
    }
    @Test
    void shouldReturnListOfBookDtoWhenGetBookCalled() {
        List<BookDto> bookDtos = bookService.getBooks();
        assertThat(1).isEqualTo(bookDtos.size());
        assertThat(bookDtos.get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("title", "test title")
                .hasFieldOrPropertyWithValue("description", "test description")
                .hasFieldOrPropertyWithValue("releaseYear", 2020);

    }

    private Book getBook() {
        Random rnd = new Random();
        return Book.builder()
                .title("test title")
                .description("test description")
                .id(rnd.nextLong())
                .releaseYear(2020)
                .build();
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
}
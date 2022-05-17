package com.wecode.bookstoremaven.service;

import com.wecode.bookstoremaven.dto.BookDto;
import com.wecode.bookstoremaven.model.Book;
import com.wecode.bookstoremaven.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookDto> getBooks(){
        Iterable<Book> all =  bookRepository.findAll();

        return StreamSupport.stream(all.spliterator(), false)
                .map(convertBookModelToBookDto())
                .collect(Collectors.toList());
    }

    private Function<Book, BookDto> convertBookModelToBookDto() {
        return book -> modelMapper.map(book, BookDto.class);
    }

    public List<BookDto> getBooksByTitle(String title) {
        Iterable<Book> all =  bookRepository.findBooksByTitleIgnoreCase(title);

        return StreamSupport.stream(all.spliterator(), false)
                .map(convertBookModelToBookDto())
                .collect(Collectors.toList());
    }
}

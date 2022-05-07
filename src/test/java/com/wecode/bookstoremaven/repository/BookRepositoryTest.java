package com.wecode.bookstoremaven.repository;

import com.wecode.bookstoremaven.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void shouldBeAbleToFetchAllBooksInDB() {
        Iterable<Book> allBooks = bookRepository.findAll();
        Long totalBookCount = StreamSupport.stream(allBooks.spliterator(),false).count();
        Assertions.assertEquals(totalBookCount,  2);

    }
    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void shouldReturnOneBookWithTitleTestTitle1() {
        Iterable<Book> allBooks = bookRepository.findBooksByTitle("TEST TITLE1");
        Long totalBookCount = StreamSupport.stream(allBooks.spliterator(),false).count();
        Assertions.assertEquals(totalBookCount,  1);

    }
}
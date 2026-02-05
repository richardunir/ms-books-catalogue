package com.missi.ms_books_catalogue.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.missi.ms_books_catalogue.data.model.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookJpaRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>{
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByCategory(String category);
    List<Book> findByIsbn(String isbn);
    List<Book> findByVisible(Boolean visible);
    List<Book> findByScoreGreaterThanEqual(Double score);
    List<Book> findByScoreLessThanEqual(Double score);
    List<Book> findByPublicDate(String publicDate);
}

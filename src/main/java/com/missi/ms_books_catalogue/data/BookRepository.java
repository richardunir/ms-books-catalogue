package com.missi.ms_books_catalogue.data;

import com.missi.ms_books_catalogue.data.utils.*;

import com.missi.ms_books_catalogue.data.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookRepository {

    private final BookJpaRepository bookJpaRepository;

    public List<Book> getBooks(){
        return bookJpaRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookJpaRepository.findById(id).orElse(null);
    }
    public Book saveBook(Book book){
        return bookJpaRepository.save(book);
    }
    public void deleteBook(Long id){
        bookJpaRepository.deleteById(id);
    }
    public List<Book> searchBooks(String title, String author, String publicDate,
                                  String category, String isbn,
                                  Double score, Boolean visible, Double price, Integer stock){

        SearchCriteria<Book> spec = new SearchCriteria<>();

        if(StringUtils.isNotBlank(title)){
            spec.add(new SearchStatement(Consts.TITLE,title, SearchOperation.MATCH));
        }

        if(StringUtils.isNotBlank(author)){
            spec.add(new SearchStatement(Consts.AUTHOR,author, SearchOperation.MATCH));
        }
        if(StringUtils.isNotBlank(publicDate)){
            spec.add(new SearchStatement(Consts.PUBLIC_DATE,publicDate, SearchOperation.MATCH));
        }
        if(StringUtils.isNotBlank(category)){
            spec.add(new SearchStatement(Consts.CATEGORY,category, SearchOperation.MATCH));
        }
        if(StringUtils.isNotBlank(isbn)){
            spec.add(new SearchStatement(Consts.ISBN,isbn, SearchOperation.MATCH));
        }
        if(score != null){
            spec.add(new SearchStatement(Consts.SCORE,score, SearchOperation.EQUAL));
        }
        if(visible != null){
            spec.add(new SearchStatement(Consts.VISIBLE,visible, SearchOperation.EQUAL));
        }
        if(price != null){
            spec.add(new SearchStatement(Consts.PRICE,price, SearchOperation.EQUAL));
        }
        if(stock != null){
            spec.add(new SearchStatement(Consts.STOCK,stock, SearchOperation.EQUAL));
        }
        return bookJpaRepository.findAll(spec);
    }
    
}

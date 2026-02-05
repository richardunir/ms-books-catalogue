package com.missi.ms_books_catalogue.service;

import java.util.List;
import com.missi.ms_books_catalogue.data.model.Book;
import com.missi.ms_books_catalogue.controller.model.BookDto;
import com.missi.ms_books_catalogue.controller.model.CreateBookRequest;

public interface BooksService {

    List<Book> getBooks(String title, String author, String publicDate, String category, String isbn, Double score, Boolean visible, Double price, Integer stock);
    Book getBook(String bookId);
    Boolean removeBook(String bookId);
    Book createBook(CreateBookRequest createBookRequest);
    Book patchBook(String bookId, String patchRequest);
    Book updateBook(String bookId, BookDto bookDto);

    
} 

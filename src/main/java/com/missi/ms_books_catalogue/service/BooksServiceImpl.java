package com.missi.ms_books_catalogue.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.missi.ms_books_catalogue.controller.model.BookDto;
import com.missi.ms_books_catalogue.data.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.missi.ms_books_catalogue.data.model.Book;
import com.missi.ms_books_catalogue.controller.model.CreateBookRequest;

@Service
@Slf4j
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Book> getBooks(String title, String author, String publicDate, String category, String isbn, Double score, Boolean visible, Double price, Integer stock) {

		if (StringUtils.hasLength(title) || StringUtils.hasLength(author) || StringUtils.hasLength(publicDate)
            || StringUtils.hasLength(category) || StringUtils.hasLength(isbn) || score != null
            || visible != null || price != null || stock != null) {
			return repository.searchBooks(title, author, publicDate, category, isbn, score, visible, price, stock);
		}

		List<Book> books = repository.getBooks();
		return books.isEmpty() ? null : books;
	}

	@Override
	public Book getBook(String bookId) {
		return repository.getBookById(Long.valueOf(bookId));
	}

	@Override
	public Boolean removeBook(String bookId) {

		Book book = repository.getBookById(Long.valueOf(bookId));

		if (book != null) {
			repository.deleteBook(book.getId());
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Book createBook(CreateBookRequest request) {

		//Otra opcion: Jakarta Validation: https://www.baeldung.com/java-validation
		if (request != null && StringUtils.hasLength(request.getTitle().trim())
				&& StringUtils.hasLength(request.getAuthor().trim())
				&& StringUtils.hasLength(request.getPublicDate().trim()) && request.getVisible() != null) {

			Book book = Book.builder()
                    .title(request.getTitle())
                    .author(request.getAuthor())
					.publicDate(request.getPublicDate())
                    .visible(request.getVisible())
                    .category(request.getCategory())
                    .isbn(request.getIsbn())
                    .score(request.getScore())
                    .price(request.getPrice())
                    .stock(request.getStock())
                    .build();

			return repository.saveBook(book);
		} else {
			return null;
		}
	}

	@Override
	public Book patchBook(String bookId, String request) {

		//PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
		Book book = repository.getBookById(Long.valueOf(bookId));
		if (book != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(book)));
				Book patched = objectMapper.treeToValue(target, Book.class);
				repository.saveBook(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating book {}", bookId, e);
                return null;
            }
        } else {
			return null;
		}
	}

	@Override
	public Book updateBook(String bookId, BookDto updateRequest) {
		Book book = repository.getBookById(Long.valueOf(bookId));
		if (book != null) {
			book.update(updateRequest);
			repository.saveBook(book);
			return book;
		} else {
			return null;
		}
	}

}
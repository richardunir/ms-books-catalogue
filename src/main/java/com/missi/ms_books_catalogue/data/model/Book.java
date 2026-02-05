package com.missi.ms_books_catalogue.data.model;

import jakarta.persistence.*;
import lombok.*;
import com.missi.ms_books_catalogue.controller.model.BookDto;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publicDate")
    private String publicDate;

    @Column(name = "category")
    private String category;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "score")
    private Double score;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "visible")
    private Boolean visible;

    public void update(BookDto dto) {
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.publicDate = dto.getPublicDate();
        this.category = dto.getCategory();
        this.isbn = dto.getIsbn();
        this.score = dto.getScore();
        this.visible = dto.getVisible();
        this.price = dto.getPrice();
        this.stock = dto.getStock();
    }

}

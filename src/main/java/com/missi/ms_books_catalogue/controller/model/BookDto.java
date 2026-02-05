package com.missi.ms_books_catalogue.controller.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class BookDto {

    private String title;
    private String author;
    private String publicDate;
    private String category;
    private String isbn;
    private Double score;
    private Boolean visible;
    private Double price;
    private Integer stock;
    
}

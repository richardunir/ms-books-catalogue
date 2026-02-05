package com.missi.ms_books_catalogue.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateBookRequest {

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

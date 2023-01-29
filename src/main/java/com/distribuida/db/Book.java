package com.distribuida.db;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String isbn;
    private String title;
    private Integer author;
    private Double price;
}

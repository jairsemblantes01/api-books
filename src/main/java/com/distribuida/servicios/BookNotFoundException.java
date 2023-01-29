package com.distribuida.servicios;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Integer id) {
        super("Book:" + id + " was not found!");
    }
}

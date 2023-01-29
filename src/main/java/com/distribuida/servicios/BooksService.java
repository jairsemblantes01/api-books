package com.distribuida.servicios;

import com.distribuida.db.Book;
import io.helidon.common.reactive.Single;
import jakarta.json.JsonArray;


public interface BooksService {
    Single<Book> findById(Integer id);
    Single<JsonArray> findAll();
    Single<Book> pushBook(Book book);

    Single<Long> editBook(Book book, Integer id);

    Single<Long> deleteBook(Integer id);
}

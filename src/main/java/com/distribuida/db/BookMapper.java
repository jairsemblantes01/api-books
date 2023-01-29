package com.distribuida.db;


import io.helidon.dbclient.DbMapper;
import io.helidon.dbclient.DbRow;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BookMapper implements DbMapper<Book> {
    private final static Logger LOGGER = Logger.getLogger(BookMapper.class.getName());

    @Override
    public Book read(DbRow dbRow) {
        var id = dbRow.column("id");
        var title = dbRow.column("title");
        var isbn = dbRow.column("isbn");
        var author = dbRow.column("author");
        var price = dbRow.column("price");
        Book book = new Book();
        book.setId(id.as(Integer.class));
        book.setTitle(title.as(String.class));
        book.setIsbn(isbn.as(String.class));
        book.setAuthor(author.as(Integer.class));
        book.setPrice(price.as(Double.class));

        return book;
    }

    @Override
    public Map<String, ?> toNamedParameters(Book book) {
        return Map.of(
                "id", book.getId(),
                "title", book.getTitle(),
                "author", book.getAuthor(),
                "isbn", book.getIsbn(),
                "price", book.getPrice()

        );
    }

    @Override
    public List<?> toIndexedParameters(Book book) {
        return List.of(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPrice()
        );
    }
}
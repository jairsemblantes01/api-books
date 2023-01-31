package com.distribuida.servicios;
import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.db.EntityUtils;
import io.helidon.common.reactive.Single;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.JsonArray;


@ApplicationScoped
public class BookRepository implements BooksService{

    private DbClient dbClient = DbConfig.dbClient();

    public Single<JsonArray> findAll() {
        var clients =  this.dbClient.execute(exec -> exec
                        .query("SELECT * FROM books WHERE id > 0")
                ).map(data -> data.as(Book.class));
        return clients.collectList().map(EntityUtils::toJsonArray);
    }

    public Single<Book> findById(Integer id) {
        return this.dbClient
                .execute(exec -> exec
                        .createGet("SELECT * FROM books WHERE id=?")
                        .addParam(id)
                        .execute()
                )
                .map(rowOptional -> rowOptional
                        .map(dbRow -> dbRow.as(Book.class)).orElseThrow(() -> new BookNotFoundException(id))
                );
    }

    public Single<Long> editBook(Book book, Integer id) {
        this.dbClient
                .execute(exec -> exec
                        .createUpdate("UPDATE books SET title=:title, author=:author, price=:price, isbn=:isbn WHERE id=:id")
                        .addParam("title", book.getTitle())
                        .addParam("author", book.getAuthor())
                        .addParam("isbn", book.getIsbn())
                        .addParam("price", book.getPrice())
                        .execute()
                );
        return Single.just(1L);
    }

    public Single<Book> pushBook(Book book) {
        return this.dbClient
                .execute(exec -> exec
                        .query("INSERT INTO books (title, isbn, author, price) VALUES (?, ?, ? ,?) RETURNING id", book.getTitle(), book.getIsbn(), book.getAuthor(), book.getPrice())

                )
                .first()
                .map(data -> {
                    book.setId(data.column("id").as(Integer.class));
                    return book;
                });
    }

    public Single<Long> deleteBook(Integer id) {
        return this.dbClient.execute(exec -> exec
                .createDelete("DELETE FROM books WHERE id = :id")
                .addParam("id", id)
                .execute()
        );
    }
}

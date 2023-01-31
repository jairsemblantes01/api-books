package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookRepository;
import io.helidon.common.reactive.Multi;
import io.helidon.common.reactive.Single;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/books")
public class BookRest {

@Inject
    private BookRepository bookService;


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Single findAll(){
        // dbConfig.test();
        return bookService.findAll();
    }



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Single<Book> findById(@PathParam("id") Integer id){
        return bookService.findById(id);
    }
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Single<Book> pushBook(Book book){
        return bookService.pushBook(book);
    }
    

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean editBook(Book book, @PathParam("id") Integer id){
        bookService.editBook(book, id);
        return true;
    }

    @DELETE
    @Path("/{id}")
    public Single<Long> deleteBook(@PathParam("id") Integer id){
        return bookService.deleteBook(id);
    }

}

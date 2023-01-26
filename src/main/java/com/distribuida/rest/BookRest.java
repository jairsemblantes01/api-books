package com.distribuida.rest;

import java.util.List;

import com.distribuida.servicios.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import com.distribuida.db.Book;
import jakarta.ws.rs.core.MediaType;
@ApplicationScoped
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookRest {
    @Inject private BookRepository bookRepository;

    @GET
    @Path("/{id}")
    public Book findById(@PathParam("id") Integer id){
        return bookRepository.findById(id);
    }

    @GET
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @POST
    public void insert(Book book){
        bookRepository.insert(book);
    }

    @PUT
    @Path("/{id}")
    public void update(Book book,@PathParam("id") Integer id){
        book.setId(id);
        bookRepository.update(book);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        bookRepository.delete(id);
    }
}
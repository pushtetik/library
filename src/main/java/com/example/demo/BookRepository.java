package com.example.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private List<Book> books=new ArrayList<>();
    @PostConstruct
    public void init(){
        books.add(new Book("title 1", "author1"));
        books.add(new Book("title 2", "author2"));
    }
    @PreDestroy
    public void stop(){
        System.out.println("ПРОИЗОШЛА ОСТАНОВКА!!! "+books.size());
    }
    public Optional<Book> findById(Long id){
        return books.stream().filter(book->book.getId().equals(id)).findFirst();
    }

    public List<Book> findAll(){
        return books;
    }

    public Book save(Book book) {
        books.add(book);
        return book;
    }

    public boolean deleteById(Long id) {
        return books.removeIf(b->b.getId().equals(id));
    }

}

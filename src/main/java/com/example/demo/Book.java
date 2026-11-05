package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

public class Book {
    private Long id;
    private String title;
    private String author;
    private static final AtomicLong count = new AtomicLong();
    public Book(String title, String author){
        this.title=title;
        this.author=author;
        this.id=count.incrementAndGet();

    }
    public Book(){
        this.id=count.incrementAndGet();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}


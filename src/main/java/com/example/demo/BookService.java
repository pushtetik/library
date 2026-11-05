package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final Counter counter;
    private static final String msg = "Книги такой нет";

    public BookService(BookRepository repository, Counter counter) {

        this.repository = repository;
        this.counter = counter;
        System.out.println("BookService создан");
    }

    public Book getBook(Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(msg));
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void deleteById(Long id){
        if (!repository.deleteById(id)){
            throw new BookNotFoundException(msg);
        }
    }

    public Book updateBook(Long id, BookRequest book) {
        Book _book=getBook(id);
        _book.setTitle(book.title());
        _book.setAuthor(book.author());
        return _book;
    }
}


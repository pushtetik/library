package com.example.demo.service;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.dto.BookRequest;
import com.example.demo.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private static final String msg = "Книги такой нет";

    public BookService(BookRepository repository) {

        this.repository = repository;

//        System.out.println("BookService создан");
    }
    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(msg));
    }
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return repository.findAll();
    }
    @Transactional
    public Book save(Book book) {
        return repository.save(book);
    }
    @Transactional
    public void deleteById(Long id){
        if (!repository.existsById(id)){
            throw new BookNotFoundException(msg);
        }
        repository.deleteById(id);
    }
    @Transactional
    public Book updateBook(Long id, BookRequest book) {
        Book _book=getBook(id);
        _book.setTitle(book.title());
        _book.setAuthor(book.author());
        _book.setIsbn(book.isbn());
        return _book;
    }
    @Transactional(readOnly = true)
    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }
    @Transactional(readOnly = true)
    public List<Book> searchByTitlePattern(String pattern){
        return repository.searchByTitlePattern(pattern);
    }
}


package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service, Counter counter){
        this.service=service;
        this.counter = counter;
    }
    private final Counter counter;
    private BookResponse toResponse(Book book){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor());
    }

   @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id){
       return toResponse(service.getBook(id));
   }

    @GetMapping
    public List<BookResponse> getBooks(){
       return (service.findAll().stream().map(b->toResponse(b)).toList());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody @Valid BookRequest request){
        Book book=new Book(request.title(), request.author());
        return toResponse(service.save(book));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
       service.deleteById(id);

    }

    @PutMapping("/{id}")
    public BookResponse putBook(@PathVariable Long id, @RequestBody @Valid BookRequest _book){
      return toResponse (service.updateBook(id, _book));
    }
}
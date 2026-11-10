package com.example.demo.controller;

import com.example.demo.dto.BookRequest;
import com.example.demo.dto.BookResponse;
import com.example.demo.service.BookService;
import com.example.demo.entity.Book;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service){
        this.service=service;

    }

    private BookResponse toResponse(Book book){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn());
    }
    @GetMapping("/author")
    public List<Book> findByAuthor(@RequestParam String author){
        return service.findByAuthor(author);
    }
    @GetMapping("/search")
    public List<BookResponse> searchByTitlePattern(@RequestParam String pattern){
        return service.searchByTitlePattern(pattern).stream()
                .map(this::toResponse)
                .toList();
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
        Book book=new Book(request.title(), request.author(), request.isbn());
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
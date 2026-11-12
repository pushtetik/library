package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books-page")
public class BooksPageController {

    private final BookService bookService;

    public BooksPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String list(@RequestParam(required=false) String q, Model model) {
        List<Book> books=(q==null || q.isBlank())? bookService.findAll():bookService.searchByTitlePattern(q);
        model.addAttribute("books",books);
        model.addAttribute("q", q);
        return "books";
    }

    @PostMapping
    public String create(@RequestParam String title,
                         @RequestParam String author,
                         @RequestParam String isbn,
                         RedirectAttributes ra) {
        try {
            bookService.save(new Book(title, author, isbn));
            ra.addFlashAttribute("message", "Книга добавлена.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Ошибка: " + e.getMessage());
        }
        return "redirect:/books-page";
    }

}
package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Loan() {
    }

    public Loan(LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, Book book, User user) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }
}

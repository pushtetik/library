package com.example.demo.dto;

import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public record LoanResponse(Long id, String title, String fullName, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {

}

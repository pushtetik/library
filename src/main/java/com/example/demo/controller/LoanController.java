package com.example.demo.controller;

import com.example.demo.dto.LoanRequest;
import com.example.demo.dto.LoanResponse;
import com.example.demo.dto.UserStatsResponse;
import com.example.demo.entity.Loan;
import com.example.demo.exception.BookAlreadyOnLoanException;
import com.example.demo.repository.LoanRepository;
import com.example.demo.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService service;
    private LoanResponse toResponse(Loan loan) {
        return new LoanResponse(loan.getId(), loan.getBook().getTitle(), loan.getUser().getFullName(),loan.getLoanDate(),loan.getDueDate(), loan.getReturnDate());
    }
    public LoanController(LoanService service) {
        this.service = service;
    }
   @GetMapping
//   @Transactional(readOnly = true)
    public List<LoanResponse> getAll(){
//        return service.findAll().stream().map(this::toResponse).toList();
        return service.findAllWithBookAndUser().stream().map(this::toResponse).toList();
    }
    @GetMapping("/{id}")
    public UserStatsResponse findByIdUserStats(@PathVariable Long id){
        return service.searchByIdUserStats(id);
    }
     @PostMapping

     @ResponseStatus(value = HttpStatus.CREATED)
    public LoanResponse createLoan(@RequestBody @Valid LoanRequest request) throws InterruptedException {
        return toResponse(service.createLoan(request));
     }
}

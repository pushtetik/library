package com.example.demo.service;

import com.example.demo.dto.LoanRequest;
import com.example.demo.dto.LoanResponse;
import com.example.demo.dto.UserStatsResponse;
import com.example.demo.entity.Book;
import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import com.example.demo.exception.BookAlreadyOnLoanException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.exception.LoanUserNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;

import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.DateTimeAtProcessing;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
public class LoanService {
    private final LoanRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final AuditService auditService;
    private final long loanDays;
    public LoanService(LoanRepository repository, UserRepository userRepository, BookRepository bookRepository, AuditService auditService, @Value("${loans.service.date}") long loanDays){this.repository=repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.auditService = auditService;
        this.loanDays = loanDays;
    }
    public static final String UserNotFound="Пользователь не найден";
    @Transactional(readOnly = true)
    public List<Loan> findAllWithBookAndUser() {
        return repository.findAllWithBookAndUser();
    }
    @Transactional(readOnly = true)
    public UserStatsResponse searchByIdUserStats(Long id) {
        User user=repository.searchByIdUserStats(id).orElseThrow(()->new LoanUserNotFoundException(UserNotFound));
        return new UserStatsResponse(user.getFullName(),user.getLoans().size());
    }
    @Transactional
    public Loan createLoan( LoanRequest request){
        auditService.log("ISSUE_ATTEMPT bookId=" + request.bookId());
        User user=userRepository.findById(request.userId()).orElseThrow(()->new LoanUserNotFoundException(UserNotFound));
        Book book=bookRepository.findById(request.bookId()).orElseThrow(()->new BookNotFoundException("Книга не найдена"));
        if (repository.existsByBookIdAndReturnDateIsNull(request.bookId())){
                throw new BookAlreadyOnLoanException("Книга уже выдана");
        }
//        Thread.sleep(5000);
        return repository.save(new Loan(LocalDate.now(),LocalDate.now().plusDays(loanDays),null,book,user));

    }
}

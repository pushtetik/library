package com.example.demo.repository;

import com.example.demo.dto.LoanResponse;
import com.example.demo.dto.UserStatsResponse;
import com.example.demo.entity.Loan;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long>{
//    @EntityGraph(attributePaths = {"book", "user"})
//    @Query("select l from Loan l")
    @Query("select l from Loan l join fetch l.book b join fetch l.user u")
    List<Loan> findAllWithBookAndUser();

    @Query("select u from User u left join fetch u.loans where u.id = :id")
    Optional<User> searchByIdUserStats(@Param("id") Long id);


    boolean existsByBookIdAndReturnDateIsNull(Long bookId);
}

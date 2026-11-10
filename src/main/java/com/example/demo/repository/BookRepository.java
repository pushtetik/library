package com.example.demo.repository;
import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthor(String author);

    boolean existsByIsbn(String aLong);

    @Query("select b from Book b where b.title Like concat (:pattern,'%')")
    List<Book> searchByTitlePattern (@Param("pattern") String pattern);
}

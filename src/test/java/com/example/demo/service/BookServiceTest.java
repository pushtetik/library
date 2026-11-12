package com.example.demo.service;

import com.example.demo.dto.BookRequest;
import com.example.demo.entity.Book;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository repository;

    @InjectMocks
    BookService service;

    @Test
    @DisplayName("getBook: возвращает книгу, если она существует")
    void getBook_returnsBook_whenExists() {
        Book book = new Book("Чистый код", "Роберт Мартин", "978-5-4461-0960-8");;
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        Book result = service.getBook(1L);

        assertThat(result).isEqualTo(book);
    }
    @Test
    @DisplayName("getBook: возвращает ошибку если книги нет")
    void getBook_returnsBook_NotExists() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getBook(1L))
                .isInstanceOf(BookNotFoundException.class);
    }
    @Test
    void deleteById_deletesWhenBookExists() {
        when(repository.existsById(1L)).thenReturn(true);
        service.deleteById(1L);
        verify(repository).deleteById(1L);

    }
    @Test
    void deleteById_deletesWhenBookNotExists() {
        when(repository.existsById(1L)).thenReturn(false);
        assertThatThrownBy(()->service.deleteById(1L)).isInstanceOf(BookNotFoundException.class);
        verify(repository, never()).deleteById(1L) ;

    }
    @Test
    void updateBook_updatesFields() {
        Book book=new Book("old title","old author","6363636");
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        BookRequest new_book=new BookRequest("new title","new author","676767");
        service.updateBook(1L, new_book);

        assertThat(book.getTitle()).isEqualTo(new_book.title());
        assertThat(book.getAuthor()).isEqualTo(new_book.author());
        assertThat(book.getIsbn()).isEqualTo(new_book.isbn());




    }
}
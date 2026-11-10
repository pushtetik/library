package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleUniqueViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, "Книга уже выдана"));
    }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(BookNotFoundException ex) {
        ErrorResponse body = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
    @ExceptionHandler(BookAlreadyOnLoanException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(BookAlreadyOnLoanException ex) {
        ErrorResponse body = new ErrorResponse(409, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
   @ExceptionHandler(LoanUserNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleNotFount(LoanUserNotFoundException ex){
       ErrorResponse body = new ErrorResponse(404, ex.getMessage());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> noValidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ErrorResponse body = new ErrorResponse(
                400,
                "Ошибка валидации",
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    @ExceptionHandler (Exception.class)
    public ResponseEntity<ErrorResponse> handleOther(Exception ex){
        log.error("Unexpected error", ex);
        ErrorResponse body = new ErrorResponse(
                500,
                "Внутренняя ошибка сервера"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);

    }


}

package com.hrm.test.shared.exception;

import com.hrm.test.shared.error.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ResponseError responseError =
                ResponseError.builder().message(ex.getMessage()).timeStamp(LocalDateTime.now()).title("Resource is not found")
                        .build();

        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        ResponseError responseError =
                ResponseError.builder().message(ex.getMessage()).timeStamp(LocalDateTime.now()).title("Constraint Violation Error")
                        .build();

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<?> handleDatabaseViolationException(org.hibernate.exception.ConstraintViolationException ex, HttpServletRequest request) {
        ResponseError responseError =
                ResponseError.builder().message(ex.getMessage()).timeStamp(LocalDateTime.now()).title("Constraint Violation Error")
                        .build();

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
}

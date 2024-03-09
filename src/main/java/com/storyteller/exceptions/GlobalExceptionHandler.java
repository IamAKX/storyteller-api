package com.storyteller.exceptions;

import com.storyteller.dto.ResponseData;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler({EntityExistsException.class, ConstraintViolationException.class, DataIntegrityViolationException.class,SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<ResponseData> handleConstraintViolationException(RuntimeException ex) {
    	ResponseData responseData = ResponseData.<String>builder()
				.statusCode(HttpStatus.CONFLICT.value()).message(ex.getLocalizedMessage()).data(null).build();
		return new ResponseEntity<>(responseData, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<ResponseData> handleEntityValidationException(EntityValidationException ex) {
        ResponseData responseData = ResponseData.<String>builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message(ex.getErrorMessage()) // Custom message from the exception
                .data(null)
                .build();
        return new ResponseEntity<>(responseData, HttpStatus.CONFLICT);
    }

	
	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseData> handleEntityNotFoundException(EntityNotFoundException ex) {
    	ResponseData responseData = ResponseData.<String>builder()
				.statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).data(null).build();
		return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData> handleException(Exception ex) {
    	ResponseData responseData = ResponseData.<String>builder()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.getMessage()).data(null).build();
		return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}





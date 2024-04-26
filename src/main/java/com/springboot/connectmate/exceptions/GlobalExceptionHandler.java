package com.springboot.connectmate.exceptions;

import com.springboot.connectmate.dtos.OldDTOS.ErrorDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handles specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDTO> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDTO, HttpStatus.NOT_FOUND);
    }

    // Handles global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDTO> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

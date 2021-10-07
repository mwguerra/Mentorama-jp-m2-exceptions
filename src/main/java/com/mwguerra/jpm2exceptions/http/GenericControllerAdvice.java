package com.mwguerra.jpm2exceptions.http;

import com.mwguerra.jpm2exceptions.http.exceptions.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {
  @ExceptionHandler({StudentNotFoundException.class})
  public ResponseEntity<String> handleStudentNotFound (final StudentNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }
}

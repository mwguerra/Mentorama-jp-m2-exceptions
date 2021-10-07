package com.mwguerra.jpm2exceptions.http.exceptions;

public class StudentNotFoundException extends RuntimeException {
  public StudentNotFoundException() {
    super("Student not found");
  }

  public StudentNotFoundException(String message) {
    super(message);
  }
}

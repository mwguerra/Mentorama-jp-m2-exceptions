package com.mwguerra.jpm2exceptions.http.controllers;

import com.mwguerra.jpm2exceptions.models.Student;
import com.mwguerra.jpm2exceptions.services.StudentService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
@Api(tags = "Students")
public class StudentController {
  private final StudentService studentService = new StudentService();

  @GetMapping
  public ResponseEntity<List<Student>> index(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
    List<Student> students = studentService.all(name, age);

    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Student> create(@RequestBody Student student) {
    Student newStudent = studentService.create(
      new Student(student.getName(), student.getAge())
    );

    return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Student> show(@PathVariable("id") UUID id) {
    Student student = studentService.find(id);

    return new ResponseEntity<>(student, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") UUID id, @RequestBody Student student) {
    studentService.update(id, student);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") UUID id) {
    studentService.delete(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}

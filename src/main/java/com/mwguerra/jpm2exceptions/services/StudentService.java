package com.mwguerra.jpm2exceptions.services;

import com.mwguerra.jpm2exceptions.database.Database;
import com.mwguerra.jpm2exceptions.http.exceptions.StudentNotFoundException;
import com.mwguerra.jpm2exceptions.interfaces.IDatabaseService;
import com.mwguerra.jpm2exceptions.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentService implements IDatabaseService<Student> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final Database db = Database.getInstance();

  @Override
  public List<Student> all() {
    return all(null, null);
  }

  public List<Student> all(String name) {
    return all(name, null);
  }

  public List<Student> all(String name, Integer age) {
    Stream<Student> filtered = db.students.stream();

    if (name != null) {
      filtered = filtered.filter(student -> student.getName().contains(name));
    }

    if (age != null) {
      filtered = filtered.filter(student -> student.getAge().equals(age));
    }

    List <Student> students = filtered.collect(Collectors.toList());

    if (students.isEmpty()) {
      throw new StudentNotFoundException("No students found");
    }

    return students;
  }

  @Override
  public Student create(Student record) {
    db.students.add(record);

    return record;
  }

  @Override
  public Student update(UUID id, Student record) {
    validateStudentIdExists(id);

    db.students.stream()
      .filter(s -> s.getId().equals(id))
      .forEach(s -> {
        s.setName(record.getName());
        s.setAge(record.getAge());
      });

    return this.find(id);
  }

  @Override
  public Student find(UUID id) {
    return db.students.stream()
      .filter(s -> s.getId().equals(id))
      .findFirst()
      .orElse(null);
  }

  @Override
  public void delete(UUID id) {
    validateStudentIdExists(id);

    db.students.removeIf(student -> student.getId().equals(id));
  }

  private void validateStudentIdExists(UUID id) {
    if (this.find(id) == null) {
      this.logger.error("Invalid student id");

      throw new StudentNotFoundException();
    }
  }
}

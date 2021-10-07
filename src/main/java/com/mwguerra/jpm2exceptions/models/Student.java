package com.mwguerra.jpm2exceptions.models;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.lang.reflect.*;

public class Student {
  private final UUID id;
  private String name;
  private Integer age;

  public Student() {
    this.id = null;
    this.name = null;
    this.age = null;
  }

  public Student(UUID id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Student(String name, Integer age) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.age = age;
  }

  public <T> T getByAccessor(String name) throws NoSuchFieldException, IllegalAccessException {
    List<String> allowed = Arrays.asList("id", "name", "age");

    if (allowed.stream().anyMatch(item -> item.equals(name))) {
      Field field = this.getClass().getDeclaredField("name");

      field.setAccessible(true);
      return (T) field.get(this);
    }

    return null;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}

package com.mwguerra.jpm2exceptions.database;

import com.mwguerra.jpm2exceptions.models.Student;

import java.util.ArrayList;
import java.util.List;

public class Database {
  private static Database db = null;

  public final List<Student> students = new ArrayList<>();

  private Database() {}

  public static Database getInstance()
  {
    return db == null ? new Database() : db;
  }
}
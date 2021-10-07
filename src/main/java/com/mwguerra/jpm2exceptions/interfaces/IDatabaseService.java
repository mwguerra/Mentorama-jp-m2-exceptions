package com.mwguerra.jpm2exceptions.interfaces;

import java.util.List;
import java.util.UUID;

public interface IDatabaseService<T> {
  List<T> all();
  T create(T record);
  T update(UUID id, T record);
  T find(UUID id);
  void delete(UUID id);
}

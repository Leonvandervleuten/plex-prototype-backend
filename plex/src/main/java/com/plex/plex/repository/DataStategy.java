package com.plex.plex.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DataStategy<T extends Identifiable<ID>, ID extends Serializable> {

  List<T> findAll();

  <S extends T> List<S> saveAll(Iterable<S> entities);

  Optional<T> findById(ID id);

  T save(T entity);
}
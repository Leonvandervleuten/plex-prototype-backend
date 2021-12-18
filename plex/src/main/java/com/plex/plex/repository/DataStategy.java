package com.plex.plex.repository;

import java.util.List;
import java.util.Optional;

public interface DataStategy<T, ID> {

  List<T> findAll();
  List<T> findAllById(Iterable<ID> ids);

  <S extends T> List<S> saveAll(Iterable<S> entities);

  Optional<T> findById(ID id);

  <S extends T> S save(S entity);
}
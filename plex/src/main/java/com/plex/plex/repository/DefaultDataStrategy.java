package com.plex.plex.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DefaultDataStrategy<T extends Identifiable<ID>, ID extends Serializable> implements DataStategy<T, ID> {

  private final List<T> data = new ArrayList<>();

  protected abstract ID getNextAvailableId();

  @Override
  public List<T> findAll() {
    return data;
  }

  @Override
  public Optional<T> findById(ID id) {
    return data.stream().filter(entry -> entry.getId().equals(id)).findFirst();
  }

  @Override
  public T save(T entity) {
    if (entity.getId() != null) {
      findById((ID) entity.getId()).ifPresent(data::remove);
    }
    else {
      entity.setId( getNextAvailableId());
    }
    data.add(entity);
    return entity;
  }

  @Override
  public <S extends T> List<S> saveAll(Iterable<S> entities) {
    List<T> savedEntities = new ArrayList<>();
    entities.forEach(entity -> savedEntities.add(this.save(entity)));
    return (List<S>) savedEntities;
  }
}
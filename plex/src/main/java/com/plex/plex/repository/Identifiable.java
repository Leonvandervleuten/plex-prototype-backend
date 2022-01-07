package com.plex.plex.repository;

import java.io.Serializable;

public interface Identifiable<ID extends Serializable> {

  ID getId();
  void setId(ID id);
}
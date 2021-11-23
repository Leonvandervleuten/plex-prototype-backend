package com.plex.restservice.challenge;

public class User {
  public Long id;
  public String name;
  public String email;

  public User(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

}

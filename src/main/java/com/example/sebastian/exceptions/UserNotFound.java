package com.example.sebastian.exceptions;

public class UserNotFound extends Exception {

  public UserNotFound(String username) {
    super("No se ha encontrado el usuario: " + username);
  }
}

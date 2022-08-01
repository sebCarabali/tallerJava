package com.example.sebastian.repository;

import com.example.sebastian.exceptions.UserNotFound;
import com.example.sebastian.model.User;

public interface UserRepository {

  boolean isValid(String username, String password);

  User findByUsername(String username) throws UserNotFound;
}

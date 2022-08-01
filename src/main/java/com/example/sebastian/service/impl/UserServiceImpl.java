package com.example.sebastian.service.impl;

import com.example.sebastian.repository.UserRepository;
import com.example.sebastian.service.UserService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService {

  @Inject private UserRepository userRepository;

  @Override
  public boolean doLogin(String username, String password) {
    return userRepository.isValid(username, password);
  }
}

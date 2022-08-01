package com.example.sebastian.service;

import com.example.sebastian.exceptions.UserNotFound;

import java.net.UnknownHostException;

public interface LinkService {
  String generateShortUrl(String longUrl, String username)
      throws UnknownHostException, UserNotFound;

  String findOriginal(String tinyUrl);
}

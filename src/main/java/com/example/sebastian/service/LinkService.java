package com.example.sebastian.service;

import com.example.sebastian.exceptions.UserNotFound;
import com.example.sebastian.model.Link;

import java.net.UnknownHostException;
import java.util.List;

public interface LinkService {
  String generateShortUrl(String longUrl, String username)
      throws UnknownHostException, UserNotFound;

  String findOriginal(String tinyUrl);

  List<Link> findByUsername(String username);

  void deleteUserLink(String username, Long idLink);
}

package com.example.sebastian.repository;

import com.example.sebastian.model.Link;

public interface LinkDAO {
  String findByLongUrl(String longUrl);

  Link save(Link link);

  String findByShortUrl(String tinyUrl);
}

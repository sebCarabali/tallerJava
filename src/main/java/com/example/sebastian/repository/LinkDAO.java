package com.example.sebastian.repository;

import com.example.sebastian.model.Link;

import java.util.List;

public interface LinkDAO {
  String findByLongUrl(String longUrl);

  Link save(Link link);

  String findByShortUrl(String tinyUrl);

  List<Link> findByUsername(String username);
}

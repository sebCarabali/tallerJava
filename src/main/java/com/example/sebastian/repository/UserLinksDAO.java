package com.example.sebastian.repository;

import com.example.sebastian.model.UserLink;

public interface UserLinksDAO {
  UserLink create(UserLink userLink);

  void deleteUserlink(String username, Long idLink);
}

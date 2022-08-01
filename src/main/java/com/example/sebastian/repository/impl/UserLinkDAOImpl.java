package com.example.sebastian.repository.impl;

import com.example.sebastian.model.UserLink;
import com.example.sebastian.repository.UserLinksDAO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserLinkDAOImpl implements UserLinksDAO {

  @PersistenceContext private EntityManager em;

  @Override
  public UserLink create(final UserLink userLink) {
    em.persist(userLink);
    em.flush();
    return userLink;
  }
}

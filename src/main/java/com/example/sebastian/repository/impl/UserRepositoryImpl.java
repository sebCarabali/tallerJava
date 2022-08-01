package com.example.sebastian.repository.impl;

import com.example.sebastian.exceptions.UserNotFound;
import com.example.sebastian.model.User;
import com.example.sebastian.repository.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserRepositoryImpl implements UserRepository {

  @PersistenceContext private EntityManager em;

  @Override
  public boolean isValid(final String username, final String password) {
    final TypedQuery<User> tq = em.createNamedQuery(User.Q_IS_VALID, User.class);
    try {
      tq.getSingleResult();
      return true;
    } catch (NoResultException e) {
      return false;
    }
  }

  @Override
  public User findByUsername(final String username) throws UserNotFound {
    //    final TypedQuery<User> tq = em.createNamedQuery(User.Q_FIND_BY_USERNAME, User.class);
    final TypedQuery<User> tq = em.createQuery("SELECT u FROM User u", User.class);
    //    tq.setParameter(User.P_USERNAME, username);
    try {
      return tq.getResultList().get(0);
    } catch (NoResultException e) {
      throw new UserNotFound(username);
    }
  }
}

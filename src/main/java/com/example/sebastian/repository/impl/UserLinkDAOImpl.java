package com.example.sebastian.repository.impl;

import com.example.sebastian.model.UserLink;
import com.example.sebastian.repository.UserLinksDAO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserLinkDAOImpl implements UserLinksDAO {

  @PersistenceContext private EntityManager em;

  @Override
  public UserLink create(final UserLink userLink) {
    em.persist(userLink);
    em.flush();
    return userLink;
  }

  @Override
  public void deleteUserlink(String username, Long idLink) {
    final TypedQuery<UserLink> tq =
        em.createQuery(
            "select ul from UserLink ul where ul.link.id = :idLink and ul.user.username = :username",
            UserLink.class);
    tq.setParameter("username", username);
    tq.setParameter("idLink", idLink);
    UserLink ul = tq.getSingleResult();
    em.remove(ul);
    em.flush();
  }
}

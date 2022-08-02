package com.example.sebastian.repository.impl;

import com.example.sebastian.model.Link;
import com.example.sebastian.repository.LinkDAO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class LinkDAOImpl implements LinkDAO {

  @PersistenceContext private EntityManager em;

  @Override
  public String findByLongUrl(final String longUrl) {
    final TypedQuery<Link> tq = em.createNamedQuery(Link.Q_FIND_BY_URL, Link.class);
    tq.setParameter(Link.P_URL, longUrl);
    try {
      final Link link = tq.getSingleResult();
      return link.getTinyUrl();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public Link save(final Link link) {
    em.persist(link);
    em.flush();
    return link;
  }

  @Override
  public String findByShortUrl(String tinyUrl) {
    final TypedQuery<Link> tq = em.createNamedQuery(Link.Q_FIND_BY_TINY_URL, Link.class);
    tq.setParameter(Link.P_TINY_URL, "%" + tinyUrl);
    try {
      final Link link = tq.getSingleResult();
      return link.getUrl();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public List<Link> findByUsername(final String username) {
    final TypedQuery<Link> tq = em.createNamedQuery(Link.Q_FIND_BY_USERNAME, Link.class);
    tq.setParameter(Link.P_USERNAME, username);
    return tq.getResultList();
  }
}

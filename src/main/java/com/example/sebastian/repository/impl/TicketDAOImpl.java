package com.example.sebastian.repository.impl;

import com.example.sebastian.model.Ticket;
import com.example.sebastian.repository.TicketDAO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class TicketDAOImpl implements TicketDAO {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public Ticket guardar() {
    final Ticket ticket = new Ticket();
    entityManager.persist(ticket);
    entityManager.flush();
    return ticket;
  }
}

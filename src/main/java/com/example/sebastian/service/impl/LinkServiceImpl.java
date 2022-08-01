package com.example.sebastian.service.impl;

import com.example.sebastian.exceptions.UserNotFound;
import com.example.sebastian.model.Link;
import com.example.sebastian.model.Ticket;
import com.example.sebastian.model.User;
import com.example.sebastian.model.UserLink;
import com.example.sebastian.repository.LinkDAO;
import com.example.sebastian.repository.TicketDAO;
import com.example.sebastian.repository.UserLinksDAO;
import com.example.sebastian.repository.UserRepository;
import com.example.sebastian.service.LinkService;
import io.seruco.encoding.base62.Base62;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.net.UnknownHostException;

@Stateless
public class LinkServiceImpl implements LinkService {

  @Inject private LinkDAO linkDAO;
  @Inject private TicketDAO ticketDAO;

  @Inject private UserRepository userRepository;

  @Inject private UserLinksDAO userLinksDAO;

  @Override
  public String generateShortUrl(final String longUrl, final String username)
      throws UnknownHostException, UserNotFound {
    String shortUrl = linkDAO.findByLongUrl(longUrl);
    if (shortUrl == null || shortUrl.trim().isEmpty()) {
      shortUrl = generateAndSave(longUrl, username);
    }
    return shortUrl;
  }

  @Override
  public String findOriginal(final String tinyUrl) {
    return linkDAO.findByShortUrl(tinyUrl);
  }

  private String generateAndSave(final String longUrl, final String username)
      throws UnknownHostException, UserNotFound {
    final Long uniqueId = generateUniqueId();
    final String base62 = generateBase62(uniqueId);
    final String shortUrl = createShortUrl(base62);
    final Link link = saveLink(longUrl, shortUrl);
    associateToUser(link, username);
    return shortUrl;
  }

  private void associateToUser(final Link link, final String username) throws UserNotFound {
    final User user;
    user = userRepository.findByUsername(username);
    final UserLink userLink = UserLink.builder().user(user).link(link).build();
    userLinksDAO.create(userLink);
  }

  private Link saveLink(final String longUrl, final String shortUrl) {
    final Link link = Link.builder().url(longUrl).tinyUrl(shortUrl).build();
    return linkDAO.save(link);
  }

  private String createShortUrl(final String shortUrl) throws UnknownHostException {
    return String.format("http://localhost:8080/tallerfinal-1.0-SNAPSHOT/r/%s", shortUrl);
  }

  private String generateBase62(final Long uniqueId) {
    final Base62 base62 = Base62.createInstance();
    final byte[] encoded = base62.encode(uniqueId.toString().getBytes());
    return new String(encoded);
  }

  @Lock(LockType.WRITE)
  private Long generateUniqueId() {
    final Ticket ticket = ticketDAO.guardar();
    return ticket.getId();
  }
}

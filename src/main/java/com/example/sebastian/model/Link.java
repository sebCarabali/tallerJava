package com.example.sebastian.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@NamedQueries({
  @NamedQuery(name = Link.Q_FIND_ALL, query = "SELECT l FROM Link l"),
  @NamedQuery(
      name = Link.Q_FIND_BY_URL,
      query = "SELECT l FROM Link l WHERE l.url = :" + Link.P_URL),
  @NamedQuery(
      name = Link.Q_FIND_BY_TINY_URL,
      query = "SELECT l FROM Link l WHERE l.tinyUrl LIKE :" + Link.P_TINY_URL)
})
@Entity
@Getter
@Setter
@Table(name = "link")
@Builder(toBuilder = true)
@AllArgsConstructor
@Cacheable
public class Link implements Serializable {

  public static final String Q_FIND_ALL = "Link.findAll";
  public static final String Q_FIND_BY_URL = "Link.findByUrl";
  public static final String Q_FIND_BY_TINY_URL = "Link.findByTinyUrl";
  public static final String P_URL = "url";
  public static final String P_TINY_URL = "tinyUrl";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "url")
  private String url;

  @Column(name = "tiny_url")
  private String tinyUrl;

  public Link() {}
}

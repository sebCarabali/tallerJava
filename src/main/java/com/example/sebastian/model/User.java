package com.example.sebastian.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQueries({
  @NamedQuery(
      query =
          "SELECT u FROM User u WHERE u.username = :"
              + User.P_USERNAME
              + " AND u.password = :"
              + User.P_PASSWORD,
      name = User.Q_IS_VALID),
  @NamedQuery(
      name = User.Q_FIND_BY_USERNAME,
      query = "SELECT u FROM User u WHERE u.username = :" + User.P_USERNAME)
})
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

  public static final String Q_FIND_BY_USERNAME = "User.findByUsername";
  public static final String Q_IS_VALID = "User.isvalid";
  public static final String P_USERNAME = "username";
  public static final String P_PASSWORD = "password";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "username", nullable = false, length = 20)
  private String username;

  @Column(name = "password", nullable = false, length = 50)
  private String password;

  //  @Column(name = "created_at")
  //  private Instant createdAt;
}

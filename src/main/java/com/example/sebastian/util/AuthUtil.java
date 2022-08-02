package com.example.sebastian.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public final class AuthUtil {

  public String findUsernameFrom(HttpServletRequest request) {
    final HttpSession session = request.getSession(false);
    String username;
    if (session != null && (username = (String) session.getAttribute("username")) != null) {
      return username;
    }
    return null;
  }
}

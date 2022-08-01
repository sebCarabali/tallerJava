package com.example.sebastian.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.stream.Stream;

// @WebFilter(
//    filterName = "AuthFilter",
//    urlPatterns = {"/*"})
public class AuthFilter { // implements Filter {

  private static final String[] loginRequiredUrls = {"/recortar", "/userlinks", "/"};

  public void init(FilterConfig config) throws ServletException {
    System.out.println("AuthFilter.init");
  }

  public void destroy() {}

  // @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    System.out.println("InAuthFilter");
    HttpServletRequest req = (HttpServletRequest) request;

    if (Stream.of(".css", ".js", ".png").anyMatch(e -> req.getRequestURI().endsWith(e))) {
      chain.doFilter(request, response);
      return;
    }

    HttpSession session = req.getSession(false);
    boolean isLoggedIn = session != null && session.getAttribute("username") != null;
    String loginURI = req.getContextPath() + "/do-login";
    boolean isLoginRequest = req.getRequestURI().equals(loginURI);
    boolean isLoginPage = req.getRequestURI().endsWith("login.jsp");
    if (isLoggedIn && (isLoginRequest || isLoginPage)) {
      request.getRequestDispatcher("index.jsp").forward(request, response);
    } else if (!isLoggedIn && isLoginRequiered(req)) {
      request.getRequestDispatcher("login.jsp").forward(request, response);
    } else {
      chain.doFilter(request, response);
    }
  }

  private boolean isLoginRequiered(HttpServletRequest request) {
    String requestURL = request.getRequestURL().toString();

    for (String loginRequiredURL : loginRequiredUrls) {
      if (requestURL.contains(loginRequiredURL)) {
        return true;
      }
    }

    return false;
  }
}

package com.example.sebastian.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(
    filterName = "AuthFilter",
    urlPatterns = {"/*"})
public class AuthFilter implements Filter {

  private static final String[] loginRequiredUrls = {"/recortar", "/userlinks", "/"};

  public void init(FilterConfig config) throws ServletException {
    System.out.println("AuthFilter.init");
  }

  public void destroy() {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession(false);
    chain.doFilter(request, response);
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

package com.example.sebastian.controller;

import com.example.sebastian.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/do-login")
public class LoginController extends HttpServlet {

  @Inject private UserService userService;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    final String username = req.getParameter("username");
    final String password = req.getParameter("password");
    HttpSession session = req.getSession(false);
    if (session == null) {
      session = req.getSession();
    } else {
      req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    if (userService.doLogin(username, password)) {
      session.setAttribute("username", username);
      req.getRequestDispatcher("index.jsp").include(req, resp);
    } else {
      req.setAttribute("error", "Usuario o Contraseña incorrectos");
      req.getRequestDispatcher("login.jsp").include(req, resp);
    }
  }
}

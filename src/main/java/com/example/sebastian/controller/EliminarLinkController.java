package com.example.sebastian.controller;

import com.example.sebastian.service.LinkService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
    name = "EliminarLinkController",
    urlPatterns = {"/links/eliminar/*"})
public class EliminarLinkController extends HttpServlet {
  @Inject private LinkService linkService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      final Long idLink = Long.valueOf(request.getPathInfo().substring(1));
      final String username = "sebcarabali";
      linkService.deleteUserLink(username, idLink);
      request.getRequestDispatcher("links/list.jsp").forward(request, response);
    } catch (NumberFormatException nfe) {
      request.getRequestDispatcher("links/list.jsp").forward(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}
}

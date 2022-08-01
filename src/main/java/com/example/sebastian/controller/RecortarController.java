package com.example.sebastian.controller;

import com.example.sebastian.exceptions.UserNotFound;
import com.example.sebastian.service.LinkService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RecortarController", urlPatterns = "/recortar")
public class RecortarController extends HttpServlet {
  @Inject private LinkService linkService;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    final String originalUrl = request.getParameter("url");
    try {
      final String shortenUrl = linkService.generateShortUrl(originalUrl, "sebcarabali");
      request.setAttribute("shortenUrl", shortenUrl);
      request.setAttribute("originalUrl", originalUrl);
      request.getRequestDispatcher("index.jsp").forward(request, response);
    } catch (UserNotFound e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("shortenUrl", "");
    req.setAttribute("originalUrl", "");
    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }
}

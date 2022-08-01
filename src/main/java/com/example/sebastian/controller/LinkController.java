package com.example.sebastian.controller;

import com.example.sebastian.service.LinkService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "/linkservlet", urlPatterns = "/r/*")
public class LinkController extends HttpServlet {

  @Inject private LinkService linkService;

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {
    final ServletContext ctx = this.getServletContext();
    final String query = req.getPathInfo();
    final String original = linkService.findOriginal(query.substring(3));
    resp.setHeader("Location", original);
    resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
  }
}

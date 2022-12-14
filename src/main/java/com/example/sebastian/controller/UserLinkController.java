package com.example.sebastian.controller;

import com.example.sebastian.model.Link;
import com.example.sebastian.service.LinkService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserLinkController", urlPatterns = "/userlinks")
public class UserLinkController extends HttpServlet {
  @Inject private LinkService linkService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    final List<Link> links = linkService.findByUsername("sebcarabali");
    links.stream().map(Link::getUrl).forEach(System.out::println);
    request.setAttribute("links", links);
    request.getRequestDispatcher("links/list.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}
}

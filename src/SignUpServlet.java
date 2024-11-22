package com.example.useraccessmanagement.controllers;

import com.example.useraccessmanagement.models.User;
import com.example.useraccessmanagement.services.UserService;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SignUpServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password, "Employee"); // Default role as Employee
        try {
            userService.registerUser(user);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=registration");
        }
    }
}

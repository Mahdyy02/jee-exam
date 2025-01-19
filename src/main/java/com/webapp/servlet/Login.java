package com.webapp.servlet;

import com.ejb.UserAuthentication;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class Login extends HttpServlet {


    @EJB
    private UserAuthentication userAuthenticationEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthenticated;
        boolean isAdmin = false;

        if(!username.equals("admin")) isAuthenticated = userAuthenticationEJB.authenticateStudent(username, password);
        else{
            isAuthenticated = userAuthenticationEJB.authenticateAdmin(username, password);
            isAdmin = true;
        }

        if (isAuthenticated) {
            if (isAdmin) {
                request.getSession().setAttribute("userID", "admin");
                response.sendRedirect("/WebApp-1.0-SNAPSHOT/jsp/admin.jsp");
            } else {
                request.getSession().setAttribute("userID", username);
                response.sendRedirect("/WebApp-1.0-SNAPSHOT/jsp/student.jsp");
            }
        } else {
            request.getSession().invalidate();
            request.setAttribute("errorMessage", "Incorrect username or password");
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }
    }
}

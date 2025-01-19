package com.webapp.servlet;

import com.dao.StudentDAO;
import com.ejb.UserAuthentication;
import com.to.Speciality;
import com.to.Student;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/add-student")
public class AddStudent extends HttpServlet {

    @Inject
    private StudentDAO studentDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String studentName = request.getParameter("studentName");
        String studentCIN = request.getParameter("studentCIN");
        int cin = Integer.parseInt(studentCIN);
        Student __ = new Student(studentName, cin, true);

        if(__.isPersistent()){
            studentDAO.persist(__);
        }

        response.sendRedirect("/WebApp-1.0-SNAPSHOT/jsp/add-student.jsp");

    }
}

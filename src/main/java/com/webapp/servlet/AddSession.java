package com.webapp.servlet;

import com.dao.*;
import com.to.Exam;
import com.to.Session;
import com.to.Student;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/add-session")
public class AddSession extends HttpServlet {

    @EJB
    private StudyManagementDAO daoManager;

    @Inject
    private SessionDAO sessionDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int examId = Integer.parseInt(request.getParameter("exam"));
            int studentId = Integer.parseInt(request.getParameter("student"));

            Exam exam = daoManager.findExamById(examId);
            Student student = daoManager.findStudentById(studentId);

            Session __ = new Session(exam, student, null, true);
            sessionDAO.merge(__);

            response.sendRedirect("/WebApp-1.0-SNAPSHOT/jsp/add-session.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

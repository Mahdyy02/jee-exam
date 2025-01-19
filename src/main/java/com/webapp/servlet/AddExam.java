package com.webapp.servlet;

import com.dao.ExamDAO;
import com.dao.StudyManagementDAO;
import com.ejb.UserAuthentication;
import com.to.Exam;
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


@WebServlet("/add-exam")
public class AddExam extends HttpServlet {

    @EJB
    private StudyManagementDAO daoManager;

    @Inject
    private ExamDAO examDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String examName = request.getParameter("examTitle");
        int specialityId = Integer.parseInt(request.getParameter("speciality"));

        Speciality speciality = daoManager.findSpecialityById(specialityId);

        Exam __ = new Exam(examName, speciality, true);

        if(__.isPersistent()){
            examDAO.persist(__);
        }

        response.sendRedirect("/WebApp-1.0-SNAPSHOT/jsp/add-exam.jsp");

    }
}

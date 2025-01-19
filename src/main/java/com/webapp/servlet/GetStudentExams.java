package com.webapp.servlet;

import com.dao.StudyManagementDAO;
import com.to.Exam;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/get-student-exams")
public class GetStudentExams extends HttpServlet {

    @EJB
    private StudyManagementDAO daoManager;

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String userIDString = (String) request.getSession().getAttribute("userID");

        if (userIDString == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"User not authenticated\"}");
            return;
        }

        int studentID;
        try {
            studentID = Integer.parseInt(userIDString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid userID format\"}");
            return;
        }


        List<Exam> exams = new ArrayList<>();

        try{
            exams = daoManager.searchAvailableExams(studentID);
        }catch(Exception e){
            e.printStackTrace();
        }

        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < exams.size(); i++) {
            Exam exam = exams.get(i);
            json.append("{");
            json.append("\"id\":").append(exam.getIdExam()).append(",");
            json.append("\"name\":\"").append(exam.getTitleExam()).append("\",");
            json.append("\"speciality\":\"").append(exam.getSpeciality().getNameSpeciality()).append("\"");
            json.append("}");
            if (i < exams.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();

    }
}

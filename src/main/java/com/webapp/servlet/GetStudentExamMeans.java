package com.webapp.servlet;

import com.ejb.StudentPerformanceAnalytics;
import com.to.Speciality;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

    @WebServlet("/get-student-statistics")
public class GetStudentExamMeans extends HttpServlet {

    @EJB
    private StudentPerformanceAnalytics studentPerformanceAnalytics;

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

        studentPerformanceAnalytics.calculateStudentPerformance(studentID);
        HashMap<Speciality, Float> meanPerSpeciality = studentPerformanceAnalytics.getMeanPerSpeciality();

        StringBuilder json = new StringBuilder();
        json.append("[");

        int i = 0;
        for (Speciality speciality : meanPerSpeciality.keySet()) {
            Float mean = meanPerSpeciality.get(speciality);
            json.append("{");
            json.append("\"speciality\":\"").append(speciality.getNameSpeciality()).append("\",");
            json.append("\"mean\":").append(mean);
            json.append("}");
            if (i < meanPerSpeciality.size() - 1) {
                json.append(",");
            }
            i++;
        }

        json.append("]");

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();

    }

}

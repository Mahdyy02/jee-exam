package com.webapp.servlet;

import com.dao.StudyManagementDAO;
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

@WebServlet("/get-statistics")
public class GetMeanPerSpeciality extends HttpServlet {

    @EJB
    private StudentPerformanceAnalytics studentPerformanceAnalytics;

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        request.setAttribute("jsonData", "[]");

        int studentId = Integer.parseInt(request.getParameter("student"));

        studentPerformanceAnalytics.calculateStudentPerformance(studentId);
        HashMap<Speciality, Float> meanPerSpeciality = studentPerformanceAnalytics.getMeanPerSpeciality();

        StringBuilder json = new StringBuilder();

        if (meanPerSpeciality.isEmpty()) {
            json.append("[]");
        } else {

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
        }

        request.setAttribute("jsonData", json.toString());

        request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);

    }

}

package com.webapp.servlet;

import com.dao.StudyManagementImplDAO;
import com.to.Exam;
import com.to.Session;
import com.to.Speciality;
import com.to.Student;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
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

@WebServlet("/get-exams")
public class GetExams extends HttpServlet {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        List<Exam> exams = new ArrayList<>();

        try{
            exams = em.createQuery("SELECT e FROM Exam e", Exam.class).getResultList();
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

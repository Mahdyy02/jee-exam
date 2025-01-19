package com.webapp.servlet;

import com.dao.StudyManagementImplDAO;
import com.to.Session;
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

@WebServlet("/get-sessions")
public class GetSessions extends HttpServlet {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        List<Session> sessions = new ArrayList<>();

        try{
            sessions = em.createQuery("SELECT s FROM Session s", Session.class).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }

        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < sessions.size(); i++) {
            Session session = sessions.get(i);
            json.append("{");
            json.append("\"ExamID\":\"").append(session.getIdExam()).append("\",");
            json.append("\"StudentID\":\"").append(session.getIdStudent()).append("\",");
            json.append("\"Exam\":\"").append(session.getExam().getTitleExam()).append("\",");
            json.append("\"Student\":\"").append(session.getStudent().getNameStudent()).append("\",");
            json.append("\"Mark\":\"").append(session.getMark()).append("\",");
            json.append("\"Classroom\":\"").append(session.getClassroom()).append("\",");
            json.append("\"Date\":").append(session.getExamDate() != null ? "\"" + session.getExamDate() + "\"" : "null");
            json.append("}");
            if (i < sessions.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();

    }
}

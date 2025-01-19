package com.webapp.servlet;

import com.dao.StudyManagementImplDAO;
import com.to.Session;
import com.to.Student;
import com.to.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
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

@WebServlet("/get-students")
public class GetStudents extends HttpServlet {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        List<Student> students = new ArrayList<>();

        try{
            students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }

        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < students.size(); i++) {
            Student Student = students.get(i);
            json.append("{");
            json.append("\"id\":").append(Student.getIdStudent()).append(",");
            json.append("\"cin\":\"").append(Student.getCin()).append("\",");
            json.append("\"name\":\"").append(Student.getNameStudent()).append("\"");
            json.append("}");
            if (i < students.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();

    }
}

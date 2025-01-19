package com.webapp.servlet;

import com.to.Speciality;
import jakarta.persistence.EntityManager;
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

@WebServlet("/get-specialities")
public class GetSpecialities extends HttpServlet {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Speciality> specialities = new ArrayList<>();

        try{
            specialities = em.createQuery("SELECT s FROM Speciality s", Speciality.class).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }

        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < specialities.size(); i++) {
            Speciality speciality = specialities.get(i);
            json.append("{");
            json.append("\"id\":").append(speciality.getIdSpeciality()).append(",");
            json.append("\"name\":\"").append(speciality.getNameSpeciality()).append("\"");
            json.append("}");
            if (i < specialities.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();

    }
}

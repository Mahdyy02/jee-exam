package com.webapp.servlet;

import com.dao.SpecialityDAO;
import com.ejb.UserAuthentication;
import com.to.Speciality;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.persistence.Transient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/add-speciality")
public class AddSpeciality extends HttpServlet {

    @Inject
    private SpecialityDAO specialityDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String specialtyName = request.getParameter("specialtyName");
        Speciality __ = new Speciality(specialtyName, true);

        if(__.isPersistent()){
            specialityDAO.persist(__);
        }

        response.sendRedirect("/WebApp-1.0-SNAPSHOT/jsp/add-speciality.jsp");
    }
}

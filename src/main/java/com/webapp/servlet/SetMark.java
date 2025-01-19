package com.webapp.servlet;

import com.dao.SessionDAO;
import com.dao.StudyManagementDAO;
import com.to.Session;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/set-mark")
public class SetMark extends HttpServlet {

    @EJB
    private StudyManagementDAO daoManager;

    @Inject
    private SessionDAO sessionDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int examID = Integer.parseInt(request.getParameter("examID"));
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        int mark = Integer.parseInt(request.getParameter("mark"));

        Session session = daoManager.findSession(null, studentID, examID);
        session.setMark(mark);
        sessionDAO.merge(session);

        response.sendRedirect("/WebApp-1.0-SNAPSHOT/jsp/set-mark.jsp");

    }
}

package com.webapp.servlet;

import com.ejb.StudentExamScheduler;
import com.dao.StudyManagementDAO;
import com.to.Exam;
import com.to.Session;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

@WebServlet("/set-schedule")
public class SetSchedule extends HttpServlet {

    @EJB
    private StudentExamScheduler examScheduler;

    @EJB
    private StudyManagementDAO daoManager;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            int classroomsNumber = Integer.parseInt(request.getParameter("classroomsNumber"));
            String beginDateString = request.getParameter("beginDate");
            String endDateString = request.getParameter("endDate");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = dateFormat.parse(beginDateString);
            Date endDate = dateFormat.parse(endDateString);

            boolean success = examScheduler.autoSchedule(classroomsNumber, beginDate, endDate);

            if (success) {
                HashMap<Date, Vector<Vector<Exam>>> schedule = examScheduler.getSchedule();

                List<Session> allSessions = daoManager.findAllSessions();

                String[] periodTimes = {
                        "08:00", "10:30", "13:00", "15:30"
                };

                for (Date scheduledDate : schedule.keySet()) {
                    Vector<Vector<Exam>> daySchedule = schedule.get(scheduledDate);

                    for (int periodIndex = 0; periodIndex < daySchedule.size(); periodIndex++) {
                        Vector<Exam> periodExams = daySchedule.get(periodIndex);

                        for (int classroomIndex = 0; classroomIndex < periodExams.size(); classroomIndex++) {

                            Exam scheduledExam = periodExams.get(classroomIndex);

                            if (scheduledExam != null) {
                                for (Session session : allSessions) {
                                    if (session.getExam().getIdExam() == scheduledExam.getIdExam() &&
                                            session.getMark() < 50)
                                    {
                                        String periodStartTime = periodTimes[periodIndex];

                                        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                        String examDateTimeString = dateFormat.format(scheduledDate) + " " + periodStartTime;
                                        Date examDateTime = timeFormat.parse(examDateTimeString);

                                        session.setExamDate(examDateTime);  // Update the session's exam date with time
                                        session.setClassroom(classroomIndex + 1);

                                        daoManager.update(session);
                                    }
                                }
                            }
                        }
                    }
                }

                response.sendRedirect("/WebApp-1.0-SNAPSHOT/jsp/view-sessions.jsp");

            } else {
                response.getWriter().write("{\"status\": \"error\", \"message\": \"Failed to set schedule\"}");
            }

        } catch (NumberFormatException | ParseException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Invalid input parameters\"}");
            e.printStackTrace();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"An error occurred\"}");
            e.printStackTrace();
        }
    }
}

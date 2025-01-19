package com.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.dao.StudyManagementDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import com.to.CompositeKey;
import com.to.Exam;
import com.to.Session;
import com.to.Student;

@Stateless
public class StudentExamScheduler {

    private HashMap<Exam, Vector<Student>> examStudentAdjacencyList = new HashMap<>();
    private HashMap<CompositeKey, Boolean> studentHasExam = new HashMap<>();
    private HashMap<Date, Vector<Vector<Exam>>> schedule = new HashMap<>();

    public HashMap<Date, Vector<Vector<Exam>>> getSchedule() {
        return schedule;
    }

    @EJB
    StudyManagementDAO daoManager;

    public Boolean autoSchedule(Integer classroomsNumber, Date beginDate, Date endDate) {
        try {

            schedule.clear();

            List<Session> sessions = daoManager.findAllSessions();

            for (Session s : sessions) {
                Student student = s.getStudent();
                Exam exam = s.getExam();

                if (s.getMark() >= 50) continue; // Student passed the exam

                examStudentAdjacencyList
                        .computeIfAbsent(exam, k -> new Vector<>())
                        .add(student);

                CompositeKey studentExamRelation = new CompositeKey(s.getIdExam(), s.getIdStudent());
                studentHasExam.put(studentExamRelation, true);
            }

            HashMap<Exam, Boolean> examScheduled = new HashMap<>();

            int totalExams = examStudentAdjacencyList.keySet().size();
            int currentExamsScheduled = 0;

            Date currentDate = beginDate;
            if (calculateDayOfWeek(currentDate) == 0)
                currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);

            while (currentDate.before(endDate) || currentDate.equals(endDate)) {
                boolean anyExamsScheduledToday = false;
                HashMap<Student, Integer> studentPeriods = new HashMap<>();

                for (int period = 1; period <= 4; period++) {
                    Vector<Vector<Exam>> daySchedule = schedule.computeIfAbsent(currentDate, k -> new Vector<>());

                    if (daySchedule.size() < period) {
                        daySchedule.add(new Vector<>());
                    }

                    int currentClassroom = 0;
                    boolean examsScheduledThisPeriod = false;

                    while (currentClassroom < classroomsNumber && currentExamsScheduled < totalExams) {
                        boolean examScheduledInClassroom = false;

                        for (Exam e : examStudentAdjacencyList.keySet()) {
                            if (Boolean.TRUE.equals(examScheduled.get(e))) continue;

                            HashMap<Student, Boolean> studentsInExam = new HashMap<>();
                            for (Student s : examStudentAdjacencyList.get(e)) {
                                studentsInExam.put(s, true);
                            }

                            Vector<Student> studentsScheduledToday = new Vector<>();
                            for (int otherClassroom = 0; otherClassroom < currentClassroom; otherClassroom++) {
                                if (daySchedule.get(period - 1).size() > otherClassroom) {
                                    Exam scheduledExam = daySchedule.get(period - 1).get(otherClassroom);
                                    if (scheduledExam != null) {
                                        studentsScheduledToday.addAll(examStudentAdjacencyList.get(scheduledExam));
                                    }
                                }
                            }

                            boolean isEligible = true;
                            for (Student s : studentsScheduledToday) {
                                if (studentsInExam.get(s) != null) {
                                    isEligible = false;
                                    break;
                                }
                            }

                            if (isEligible) {
                                for (Student s : examStudentAdjacencyList.get(e)) {
                                    int periods = studentPeriods.getOrDefault(s, 0);
                                    if (periods >= 2) {
                                        isEligible = false;
                                        break;
                                    }
                                }
                            }

                            if (isEligible) {
                                while (daySchedule.get(period - 1).size() <= currentClassroom) {
                                    daySchedule.get(period - 1).add(null);
                                }

                                daySchedule.get(period - 1).set(currentClassroom, e);

                                for (Student s : examStudentAdjacencyList.get(e)) {
                                    studentPeriods.put(s, studentPeriods.getOrDefault(s, 0) + 1);
                                }

                                examScheduled.put(e, true);
                                currentExamsScheduled++;
                                examScheduledInClassroom = true;
                                examsScheduledThisPeriod = true;
                                anyExamsScheduledToday = true;
                                break;
                            }
                        }

                        if (!examScheduledInClassroom) break;

                        currentClassroom++;
                    }

                    if (!examsScheduledThisPeriod) {
                        break;
                    }
                }

                if (!anyExamsScheduledToday && currentExamsScheduled >= totalExams) break;

                currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);
                if (calculateDayOfWeek(currentDate) == 0) {
                    currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @SuppressWarnings("deprecation")
    private int calculateDayOfWeek(Date date) {
        int day = date.getDate();
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;

        if (month < 3) {
            month += 12;
            year--;
        }

        int K = year % 100;
        int J = year / 100;

        int h = (day + (13 * (month + 1)) / 5 + K + K / 4 + J / 4 + 5 * J) % 7;

        return (h + 5) % 7;
    }
}

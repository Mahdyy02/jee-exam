package com.dao;

import java.util.Date;
import java.util.List;

import com.to.*;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class StudyManagementImplDAO implements StudyManagementDAO {

    @Inject
    private ExamDAO examDAO;

    @Inject
    private SessionDAO sessionDAO;

    @Inject
    private StudentDAO studentDAO;

    @Inject
    private SpecialityDAO specialityDAO;

    public boolean addStudent(Student student){
        return studentDAO.persist(student);
    }

    public boolean addSpeciality(Speciality speciality){
        return specialityDAO.persist(speciality);
    }

    public boolean addExam(Exam exam){
        return examDAO.persist(exam);
    }

    public Student findStudentById(int idStudent){
        return studentDAO.find(idStudent);
    }

    public Speciality findSpecialityById(int idSpeciality){
        return specialityDAO.find(idSpeciality);
    }

    public Exam findExamById(int idExam){
        return examDAO.find(idExam);
    }

    public boolean addSession(Date dateOfTheExam, Student studentFound, Exam examFound){
        Session session = new Session(examFound, studentFound, dateOfTheExam);
        sessionDAO.persist(session);
        return false;
    }

    public Session findSession(Date dateOfTheExam, int idStudent, int idExam){
        CompositeKey key = new CompositeKey(idExam, idStudent);
        return sessionDAO.find(key);
    }

    public boolean update(Session sessionFound){
        return sessionDAO.merge(sessionFound);
    }

    public List<Session> findAllSessions(){
        return sessionDAO.findAllSessions();
    }

    public List<Exam> searchAvailableExams(int studentID) {
        return studentDAO.searchExamsMustTake(studentID);
    }

    public List<Exam> searchExamsTaken(int studentID) {
        return studentDAO.searchExamsTaken(studentID);
    }

}

package com.dao;

import com.to.Exam;
import com.to.Session;
import com.to.Speciality;
import com.to.Student;

import java.util.Date;
import java.util.List;


public interface StudyManagementDAO {

    boolean addStudent(Student student);
    boolean addSpeciality(Speciality speciality);
    boolean addExam(Exam exam);
    Student findStudentById(int idStudent);
    Exam findExamById(int idExam);
    boolean addSession(Date dateOfTheExam, Student studentFound, Exam examFound);
     Session findSession(Date dateOfTheExam, int idStudent, int idExam);boolean update(Session sessionFound);
    List<Session> findAllSessions();
    List<Exam> searchAvailableExams(int studentID);
    List<Exam> searchExamsTaken(int studentID);
    Speciality findSpecialityById(int idSpeciality);


}
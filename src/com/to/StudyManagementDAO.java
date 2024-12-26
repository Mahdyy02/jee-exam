package com.to;

import java.util.Date;


public interface StudyManagementDAO {

    boolean addStudent(Student student);
    boolean addSpeciality(Speciality speciality);
    boolean addExam(Exam exam);
    Student findStudentById(String idStudent);
    Exam findExamById(int idExam);
    boolean addSession(Date dateOfTheExam, Student studentFound, Exam examFound);
    Session findSession(Date dateOfTheExam, String idStudent, int idExam);
    boolean update(Session sessionfound);
    
}
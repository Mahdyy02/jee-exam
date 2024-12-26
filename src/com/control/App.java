package com.control;

// Hibernate -> JPA
// MYSQL connection
// MYSQL server

import java.util.Date;

import com.to.Exam;
import com.to.Speciality;
import com.to.Student;
import com.to.Session;
import com.to.StudyManagementImplDAO;

public class App {
    public static void main(String[] args) {
        
        if(HibernateUtil.initalize()){
            System.err.println("Database sucessfully connected");
        }else{
            System.err.println("Database connection failed");
        }

        // Create Speciality instances
        Speciality csSpeciality = new Speciality("Computer Science");
        Speciality swSpeciality = new Speciality("Software Engineering");

        // Create Exam instances
        Exam javaExam = new Exam("Java Programming Language", csSpeciality);
        Exam algoExam = new Exam("Data Structures and Algorithms", csSpeciality);
        Exam JEEExam = new Exam("JEE Framework", swSpeciality);

        // Create Student instances
        Student student1 = new Student("Ahmed Mohsen");
        Student student2 = new Student("Nour Allah");

        // Initialize DAO
        StudyManagementImplDAO studyManagement = new StudyManagementImplDAO();

        // 1. Save Specialities
        System.out.println("Saving Specialities...");
        studyManagement.addSpeciality(csSpeciality); // save to database
        studyManagement.addSpeciality(swSpeciality); // save to database

        // 3. Establish Many-to-Many Relationship between Exams and Students
        System.out.println("Associating Students with Exams...");
        javaExam.getListStudents().add(student1);  // Ahmed takes Java Exam
        JEEExam.getListStudents().add(student1);  // Ahmed takes JEE Exam
        javaExam.getListStudents().add(student2);  // Nour takes Java Exam
        algoExam.getListStudents().add(student2);  // Nour takes Algorithms Exam

        student1.getListExams().add(javaExam);
        student1.getListExams().add(JEEExam);
        student2.getListExams().add(javaExam);
        student2.getListExams().add(algoExam);

        // Save updated exams with students linked
        studyManagement.addExam(javaExam);
        studyManagement.addExam(algoExam);
        studyManagement.addExam(JEEExam);

        // Save Students
        System.out.println("Saving Students...");
        studyManagement.addStudent(student1);
        studyManagement.addStudent(student2);

        // 4. Create and save Session (Exam, Student, Date)
        Date examDate1 = new Date();  // Today
        Date examDate2 = new Date(System.currentTimeMillis() + 86400000); // number ms in a day
        
        // Session for Ahmed (Java Exam)
        System.out.println("Creating session for Ahmed (Java Exam)...");
        studyManagement.addSession(examDate1, student1, javaExam);
        
        // Session for Nour (Java Exam)
        System.out.println("Creating session for Nour (Java Exam)...");
        studyManagement.addSession(examDate1, student2, javaExam);
        
        // Session for Ahmed (JEE Exam)
        System.out.println("Creating session for Ahmed (JEE Exam)...");
        studyManagement.addSession(examDate2, student1, JEEExam);
        
        // Session for Nour (Algorithms Exam)
        System.out.println("Creating session for Nour (Algorithms Exam)...");
        studyManagement.addSession(examDate2, student2, algoExam);

        // 5. Retrieve Students and their Exams
        System.out.println("Retrieving students and their associated exams...");
        Student retrievedStudent1 = studyManagement.findStudentById(student1.getIdStudent());
        Student retrievedStudent2 = studyManagement.findStudentById(student2.getIdStudent());

        System.out.println("Student 1: " + retrievedStudent1.getNameStudent());
        for (Exam exam : retrievedStudent1.getListExams()) { // foreach loop
            System.out.println(" - " + exam.getTitleExam());
        }

        System.out.println("Student 2: " + retrievedStudent2.getNameStudent());
        for (Exam exam : retrievedStudent2.getListExams()) {
            System.out.println(" - " + exam.getTitleExam());
        }

        // 6. Retrieve Exams and their Students
        System.out.println("Retrieving exams and their associated students...");
        Exam retrievedJavaExam = studyManagement.findExamById(javaExam.getIdExam());
        System.out.println("Java Exam has the following students:");
        for (Student s : retrievedJavaExam.getListStudents()) {
            System.out.println(" - " + s.getNameStudent());
        }

        // 7. Retrieve Session by student and exam
        System.out.println("Retrieving Session for Ahmed in Java Exam...");
        Session sessionForAhmed = studyManagement.findSession(examDate1, student1.getIdStudent(), javaExam.getIdExam());
        if (sessionForAhmed != null) {
            System.out.println("Session found: " + sessionForAhmed.getExamDate() + " for " + sessionForAhmed.getMark());
        } else {
            System.out.println("No session found for Ahmed in Java Exam.");
        }

        // 8. Update a session's mark
        System.out.println("Updating mark for Ahmed's session in Java Exam...");
        sessionForAhmed.setMark(95.5f); 
        studyManagement.update(sessionForAhmed);

        // 9. Verify the updated session
        Session updatedSession = studyManagement.findSession(examDate1, student1.getIdStudent(), javaExam.getIdExam());
        if (updatedSession != null) {
            System.out.println("Updated Session for Ahmed in Java Exam: Mark = " + updatedSession.getMark());
        } else {
            System.out.println("No session found for Ahmed in Java Exam.");
        }

    }
}

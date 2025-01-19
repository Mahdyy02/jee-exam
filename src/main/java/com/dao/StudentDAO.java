package com.dao;

import com.to.Exam;
import com.to.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class StudentDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Boolean persist(Student student) {
        try{
            em.persist(student);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Boolean remove(Student student) {
        try{
            em.remove(student);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Student find(int studentID) {
        try{
            Student student = em.find(Student.class, studentID);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Transactional
    public Boolean merge(Student student) {
        try{
            em.merge(student);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Transactional
    public List<Exam> searchExamsMustTake(int studentID) {
        List<Exam> exams;
        try{
            exams = em.createQuery(
                    "SELECT s.exam FROM Session s WHERE s.mark < 50 AND s.student.idStudent = :studentID", Exam.class)
                    .setParameter("studentID", studentID)
                    .getResultList();

        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }

        return exams;
    }

    @Transactional
    public List<Exam> searchExamsTaken(int studentID) {

        List<Exam> exams;

        try{

            exams = em.createQuery(
                            "SELECT s.exam FROM Session s WHERE s.mark >= 0 AND s.student.idStudent = :studentID", Exam.class)
                    .setParameter("studentID", studentID)
                    .getResultList();

        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }

        return exams;
    }

}

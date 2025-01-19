package com.dao;

import com.to.Exam;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Stateless
public class ExamDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Boolean persist(Exam exam) {

        try{
            em.persist(exam);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Transactional
    public Boolean remove(Exam exam) {

        try{
            em.remove(exam);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Transactional
    public Exam find(int examID) {

        try{
            Exam exam = em.find(Exam.class, examID);

            return exam;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    public Boolean merge(Exam exam) {

        try{
            em.merge(exam);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}

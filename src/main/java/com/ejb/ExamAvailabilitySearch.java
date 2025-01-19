package com.ejb;

import com.dao.StudyManagementDAO;
import com.to.Exam;
import com.to.Student;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class ExamAvailabilitySearch {

    @EJB
    StudyManagementDAO daoManager;

    public List<Exam> searchAvailableExams(Student s) {
        return daoManager.searchAvailableExams(s.getIdStudent());
    }

}

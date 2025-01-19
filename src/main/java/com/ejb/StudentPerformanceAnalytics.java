package com.ejb;

import com.dao.StudyManagementDAO;
import com.to.Exam;
import com.to.Session;
import com.to.Speciality;
import com.to.Student;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

@Stateful
public class StudentPerformanceAnalytics {

    @EJB
    private StudyManagementDAO daoManager;

    private HashMap<Speciality, Float> meanPerSpeciality = new HashMap<>();

    public HashMap<Speciality, Float> getMeanPerSpeciality() {
        return meanPerSpeciality;
    }

    public Boolean calculateStudentPerformance(int studentID){

        try{

            List<Exam> studentExams = daoManager.searchExamsTaken(studentID);

            HashMap<Speciality, List<Exam>> examsPerSpeciality = new HashMap<>();

            for (Exam exam : studentExams) {
                examsPerSpeciality
                        .computeIfAbsent(exam.getSpeciality(), k -> new ArrayList<>())
                        .add(exam);
            }

            HashMap<Speciality, Vector<Float>> marksPerSpeciality = new HashMap<>();
            for(Speciality speciality : examsPerSpeciality.keySet()) {
                for(Exam exam : examsPerSpeciality.get(speciality)) {
                    Session session = daoManager.findSession(null, studentID, exam.getIdExam());
                    marksPerSpeciality
                            .computeIfAbsent(speciality, k -> new Vector<>())
                            .add(session.getMark());
                }
            }

            for (Speciality speciality : marksPerSpeciality.keySet()) {
                Vector<Float> marks = marksPerSpeciality.get(speciality);

                int size = marks.size();
                SegmentTree segmentTree = new SegmentTree(size);
                for (int i = 0; i < size; i++) {
                    segmentTree.update(i, marks.get(i), 0, 0, size - 1);
                }

                SegmentTree.Node result = segmentTree.query(0, size - 1, 0, 0, size - 1);
                float mean = result.sum/result.cnt;
                meanPerSpeciality.put(speciality, mean);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

}

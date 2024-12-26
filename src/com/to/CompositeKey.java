package com.to;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {

    @Column(name = "exam_id")
    private int examId;
    
    @Column(name = "student_id", length = 36)
    private String studentId;

    public CompositeKey() {}

    public CompositeKey(int examId, String studentId) {
        this.examId = examId;
        this.studentId = studentId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return examId == that.examId && 
               studentId != null && studentId.equals(that.studentId);
    }
    
    @Override
    public int hashCode() {
        int result = Integer.hashCode(examId);  
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        return result;
    }
}

package com.to;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {

    @Column(name = "exam_id")
    private int examId;

    @Column(name = "student_id")
    private int studentId;

    public CompositeKey() {}

    public CompositeKey(int examId, int studentId) {
        this.examId = examId;
        this.studentId = studentId;
    }

    public int getExamId() {
        return examId;
    }


    public int getStudentId() {
        return studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return examId == that.examId && 
               studentId == that.studentId;
    }
    
    @Override
    public int hashCode() {
        int result = Integer.hashCode(examId);  
        result = 31 * result + Integer.hashCode(studentId);
        return result;
    }

    @Override
    public String toString() {
        return "{" + this.examId + ", " + this.studentId + "}";
    }
}

package com.to;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "session_table")
public class Session {

    @EmbeddedId
    private CompositeKey compositeKey;

    // @ManyToOne
    // @MapsId("examId")  
    // @JoinColumn(name = "exam_id")  
    // private Exam exam;

    // @ManyToOne
    // @MapsId("studentId")  
    // @JoinColumn(name = "student_id") 
    // private Student student;

    @Temporal(TemporalType.DATE)
    private Date examDate;

    private float mark;

    public Session() {}

    public Session(Exam exam, Student student, Date examDate) {
        // this.exam = exam;
        // this.student = student;
        this.compositeKey = new CompositeKey(exam.getIdExam(), student.getIdStudent());
        this.examDate = examDate;
    }

    public void setExam(Exam exam) {
        this.compositeKey.setExamId(exam.getIdExam());;
    }
    
    public void setMark(float mark) {
        this.mark = mark;
    }

    public void setStudent(Student student) {
        this.compositeKey.setStudentId(student.getIdStudent());;
    }

    public int getIdExam() {
        return this.compositeKey.getExamId();
    }

    public float getMark() {
        return mark;
    }

    public String getIdStudent() {
        return this.compositeKey.getStudentId();
    }

    public CompositeKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(CompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    public Date getExamDate() {
        return this.examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
}
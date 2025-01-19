package com.to;

import jakarta.persistence.*;
import java.util.Date;

@Entity

@Table(name = "session_table")
public class Session {

    @EmbeddedId
    private CompositeKey compositeKey;

    @ManyToOne()
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    private Date examDate;
    private float mark = -1;
    private int classroom = -1;

    @Transient
    private Boolean persistent = false;

    public Session() {}

    public Session(Exam exam, Student student, Date examDate, Boolean persistent) {
        this.exam = exam;
        this.student = student;
        this.compositeKey = new CompositeKey(exam.getIdExam(), student.getIdStudent());
        this.examDate = examDate;
        this.persistent = persistent;
    }

    public Session(Exam exam, Student student, Date examDate) {
        this(exam, student, examDate, false);
    }
    
    public void setMark(float mark) {
        this.mark = mark;
    }

    public int getIdExam() {
        return this.compositeKey.getExamId();
    }

    public float getMark() {
        return mark;
    }

    public int getIdStudent() {
        return this.compositeKey.getStudentId();
    }

    public CompositeKey getCompositeKey() {
        return compositeKey;
    }

    public Date getExamDate() {
        return this.examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Exam getExam() {
        return exam;
    }

    public Student getStudent() {
        return student;
    }

    public Boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(Boolean persistent) {
        this.persistent = persistent;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public int getClassroom() {
        return classroom;
    }
}
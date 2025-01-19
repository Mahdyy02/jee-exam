package com.to;

import jakarta.persistence.*;
import java.util.Objects;

@Entity

@Table(name = "exam_table")
public class Exam {

    public Exam() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private int idExam;

    @Column(name = "exam_title", length = 40, nullable = false)
    private String titleExam;

    @ManyToOne()
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @Transient
    private Boolean persistent = false;

    public Exam(String titleExam, Speciality speciality, Boolean persistent) {
        this.titleExam = titleExam;
        this.speciality = speciality;

        this.persistent = persistent;
    }

    public Exam(String titleExam, Speciality speciality) {
        this(titleExam, speciality, false);
    }

    public void setIdExam(int idExam, Boolean persistent) {
        this.idExam = idExam;

    }

    public void setTitleExam(String titleExam) {
        this.titleExam = titleExam;
    }

    public int getIdExam() {
        return idExam;
    }

    public String getTitleExam() {
        return titleExam;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(Boolean persistent) {
        this.persistent = persistent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(idExam, exam.idExam) || Objects.equals(titleExam, exam.titleExam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExam, titleExam);
    }

    @Override
    public String toString() {
        return this.titleExam + "( " + this.idExam + " )";
    }

}

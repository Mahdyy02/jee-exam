package com.to;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

@Table (name="exam_table")
public class Exam {

    public Exam() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExam;
    private String titleExam;

    @ManyToOne 
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @ManyToMany(mappedBy = "listExams", fetch = FetchType.EAGER)
    @Column(name= "student_id")
    private List<Student> listStudents = new ArrayList<>();

    public Exam(String titleExam, Speciality speciality){
        this.titleExam = titleExam;
        this.speciality = speciality;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public void setTitleExam(String titleExam) {
        this.titleExam = titleExam;
    }

    public int getIdExam() {
        return idExam;
    }

    public List<Student> getListStudents() {
        return listStudents;
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

}

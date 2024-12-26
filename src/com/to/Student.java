package com.to;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity

@Table (name="student_table")
public class Student {

    public Student(){}

    @Id
    @Column(name = "student_id", length = 36)
    private String idStudent;

    private String nameStudent;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Exam> listExams = new ArrayList<>();

    public Student(String nameStudent){
        this.nameStudent = nameStudent;
        this.idStudent = UUID.randomUUID().toString();
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }
    
    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public List<Exam> getListExams() {
        return listExams;
    }

    public String getNameStudent() {
        return nameStudent;
    }

}

package com.to;

import jakarta.persistence.*;
import java.util.Objects;

@Entity

@Table(name = "student_table")
public class Student {

    public Student(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private int idStudent;

    @Column(name = "student_name")
    private String nameStudent;

    @Column(unique = true)
    private int cin;

    @Transient
    private Boolean persistent = false;

    public Student(String nameStudent, int cin, Boolean persistent) {
        this.nameStudent = nameStudent;
        this.idStudent = 0;
        this.persistent = persistent;
        this.cin = cin;
    }

    public Student(String nameStudent, int cin) {
        this(nameStudent, cin, false);
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getCin() {
        return cin;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    @Override
    public String toString() {
        return nameStudent + "(" + idStudent + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; 

        Student student = (Student) o;
        return Objects.equals(idStudent, student.idStudent); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent); 
    }

    public Boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(Boolean persistent) {
        this.persistent = persistent;
    }

}

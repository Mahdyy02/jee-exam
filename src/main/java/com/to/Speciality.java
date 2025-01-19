package com.to;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity

@Table(name = "speciality_table")
public class Speciality {

    public Speciality(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speciality_id")
    private int idSpeciality;
    private String nameSpeciality;

    @OneToMany(mappedBy = "idExam")
    @Column(name = "speciality_id")
    private List<Exam> listExams = new ArrayList<>();

    @Transient
    private Boolean persistent = false;


    public Speciality(String nameSpeciality, Boolean persistent) {
        this.nameSpeciality = nameSpeciality;
        this.persistent = persistent;
    }

    public Speciality(String nameSpeciality) {
        this(nameSpeciality, false);
    }

    public void setNameSpeciality(String nameSpeciality) {
        this.nameSpeciality = nameSpeciality;
    }

    public int getIdSpeciality() {
        return idSpeciality;
    }

    public List<Exam> getListExams() {
        return listExams;
    }
    public String getNameSpeciality() {
        return nameSpeciality;
    }

    public Boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(Boolean persistent) {
        this.persistent = persistent;
    }

    @Override
    public String toString() {
        return this.nameSpeciality + "( " + this.idSpeciality + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return idSpeciality == that.idSpeciality && Objects.equals(nameSpeciality, that.nameSpeciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSpeciality, nameSpeciality);
    }

}

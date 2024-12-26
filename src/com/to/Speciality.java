package com.to;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table (name="speciality_table")
public class Speciality {

    public Speciality(){}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSpeciality;
    private String nameSpeciality;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.PERSIST)
    private List<Exam> listExams = new ArrayList<>();

    public Speciality(String nameSpeciality){
        this.nameSpeciality = nameSpeciality;
    }

    public void setIdSpeciality(int idSpeciality) {
        this.idSpeciality = idSpeciality;
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

}

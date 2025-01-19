package com.dao;

import com.to.Speciality;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Stateless
public class SpecialityDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Boolean persist(Speciality speciality) {
        try{
            em.persist(speciality);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Boolean remove(Speciality speciality) {
        try{
            em.remove(speciality);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Speciality find(int specialityID) {

        try{
            Speciality speciality = em.find(Speciality.class, specialityID);
            return speciality;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    public Boolean merge(Speciality speciality) {
        try{
            em.merge(speciality);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}

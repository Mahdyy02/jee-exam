package com.dao;

import com.to.CompositeKey;
import com.to.Session;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class SessionDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Boolean persist(Session session) {

        try{
            em.persist(session);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Transactional
    public Boolean remove(Session session) {
        try{
            em.remove(session);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    @Transactional
    public Session find(CompositeKey sessionID) {
        try{
            Session session = em.find(Session.class, sessionID);
            return session;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    public Boolean merge(Session session) {
        try{
            em.merge(session);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Transactional
    public List<Session> findAllSessions(){
        List<Session> sessions;

        try{
            sessions = em.createQuery("SELECT s FROM Session s", Session.class).getResultList();
        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }

        return sessions;
    }

}

package com.control;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static Configuration configuration;
    public static StandardServiceRegistryBuilder builder;
    public static SessionFactory factory;
    public static Session session;
    public static Transaction transaction;

    public static Boolean initalize(){

        try{
            configuration = new Configuration().configure();
            configuration.addAnnotatedClass(com.to.Exam.class);
            configuration.addAnnotatedClass(com.to.Session.class);
            configuration.addAnnotatedClass(com.to.Speciality.class);
            configuration.addAnnotatedClass(com.to.Student.class);
            configuration.addAnnotatedClass(com.to.CompositeKey.class);
            builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    
            factory = configuration.buildSessionFactory(builder.build());

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }   

    public static Boolean save(Object o){

        try {

            session = HibernateUtil.factory.openSession();
            transaction = HibernateUtil.session.beginTransaction();

            session.save(o);
            
            transaction.commit();
            session.close();

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public static Boolean update(Object o){

        try {

            session = HibernateUtil.factory.openSession();
            transaction = HibernateUtil.session.beginTransaction();

            session.update(o);
            
            transaction.commit();
            session.close();

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public static Object find(Class<?> c, Object primaryKey) {
        try {
            session = HibernateUtil.factory.openSession();
            transaction = HibernateUtil.session.beginTransaction();
    
            Object result = session.get(c, (Serializable)primaryKey);
    
            transaction.commit();
            session.close();
    
            return result;
    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

}
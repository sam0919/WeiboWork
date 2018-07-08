/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myHibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Administrator
 */
public class HibernateUtil {
 private static final Configuration configuration ;
    private static final SessionFactory sessionFactory ;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) config file.
            // And registe from standard service.
            configuration = new Configuration();
            configuration.configure(); 
            ServiceRegistry  sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();             
            sessionFactory = configuration.buildSessionFactory(sr); 
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

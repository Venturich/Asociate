package com.asociate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Ventura
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
          Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
             sessionFactory = configuration.buildSessionFactory(ssrb.build());
        return sessionFactory;
    }
}

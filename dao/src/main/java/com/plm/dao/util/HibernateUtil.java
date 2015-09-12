package com.plm.dao.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plm.dao.tournament.BlindStructureDaoImpl;
  /**
   * Class containing singleton on session factory. It also contains some utility method for hibernate
   * @author Alexandre Lefèvre "Wodric"
   *
   */
public class HibernateUtil {
  
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
  
    private static Logger logger = LoggerFactory.getLogger(BlindStructureDaoImpl.class);
    /**
     * create the session factory for HibernateUtil class
     * @return the sesison factory created
     */
    private static SessionFactory buildSessionFactory() {
        try {
        	 Configuration configuration = new Configuration();
        	 configuration.configure("hibernate.cfg.xml");
            // Create the SessionFactory from hibernate.cfg.xml
        	serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
        	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
        	logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
  
    /**
     * Get a session from HibernateUtil session factory
     * @return
     */
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
        	buildSessionFactory();
        }
    	return sessionFactory;
    }
    
    /**
     * Get a session with commit flush mode from HibernateUtil session factory
     * @return the session in auto flush mode
     */
    public static Session getCommitFlushModeSession(){
    	if(sessionFactory == null){
    		getSessionFactory();
    	}
    
    	Session flusedModeSession = sessionFactory.openSession();
    	flusedModeSession.setFlushMode(FlushMode.COMMIT);
    	return flusedModeSession;
    }
    
    /**
     * Get a session with auto flush mode from HibernateUtil session factory
     * @return the session in auto flush mode (default mode)
     */
    public static Session getAutoFlushModeSession(){
    	if(sessionFactory == null){
    		getSessionFactory();
    	}
    	
    	return sessionFactory.openSession();
    }
  
    /**
     * shutdown the session factory
     */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
    
    /**
     * set a session session factory
     * very useful to test persistence stack
     * This will be the session factory for all transaction be very careful!
     * @param newSessionFactory add a new session factory. 
     */
    public static void setSessionFactory(SessionFactory newSessionFactory) {
    	sessionFactory = newSessionFactory;
    }
  
}
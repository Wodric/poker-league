package com.plm.dao;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;

import com.plm.dao.util.HibernateUtil;

/**
 * PArent class to all DAO test. It permit to create in memory database only for test
 * Must extend all DAO class to use hibernate-testing.cfg as configuration file
 * @author Alexandre Lefèvre "Wodric"
 *
 */
public class DAOTest {
	
	@BeforeClass
	public static void initHsqldb(){
	   	 Configuration configuration = new Configuration();
	   	 configuration.configure("hibernate-testing.cfg.xml");
	   	 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
		 HibernateUtil.setSessionFactory(configuration.buildSessionFactory(serviceRegistry));
	}
}

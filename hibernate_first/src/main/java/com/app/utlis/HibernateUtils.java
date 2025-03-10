package com.app.utlis;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateUtils {
	
	public static Session getConfiguration(){
		//automatically mapping file will be loaded as we registered this mapping xml
		//in the cfg.xml file
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		//this is secondary memory for cache mechanism and to established this connection
		//based on configuration class
		SessionFactory sf= cfg.buildSessionFactory();
		//internally  a database connection be open for statement execution
		//first level cache mechanism
		Session session =sf.openSession();
		
		return session;
	}

}

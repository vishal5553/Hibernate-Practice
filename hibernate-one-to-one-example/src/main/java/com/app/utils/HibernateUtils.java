package com.app.utils;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.app.model.Pancard;
import com.app.model.Person;

public class HibernateUtils {

	public static Session getConfiguration() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/java_nov_batch");
		properties.setProperty("hibernate.connection.username", "root");
		properties.setProperty("hibernate.connection.password", "mysql");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		// automatically mapping file will be loaded as we registered this mapping xml
		// in the cfg.xml file.
		Configuration cfg = new Configuration();
		cfg.addProperties(properties);
		cfg.addAnnotatedClass(Person.class);
		cfg.addAnnotatedClass(Pancard.class);

		// this is secondory memory for cache machanism and to established connection
		// based on configuration class.
		SessionFactory sf = cfg.buildSessionFactory();

		// internally a database connection be opened for statement execution. //first
		// level cache machanism
		Session session = sf.openSession();

		return session;
	}

}

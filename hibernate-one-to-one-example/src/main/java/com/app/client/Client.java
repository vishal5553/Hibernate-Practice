package com.app.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.model.Pancard;
import com.app.model.Person;
import com.app.utils.HibernateUtils;

public class Client {

	public static void main(String[] args) {
		save();
		findAll();
	}

	public static void save() {

		Person person = new Person("Amit");

		Pancard pancard = new Pancard("ASDFG1023D");
		pancard.setPerson(person);

		person.setPancard(pancard);

		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();

		session.save(person);

		tx.commit();
		if (tx.wasCommitted())
			System.out.println("success");
		else
			System.out.println("failure");

	}

	@SuppressWarnings("unchecked")
	public static void findAll() {
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(Person.class);
		List<Person> list = cr.list();
		for (Person person : list) {
			System.out.println(person);
			System.out.println("\t" + person.getPancard());
		}
		System.out.println("_______________________________________________");
		Criteria cr1 = session.createCriteria(Pancard.class);
		List<Pancard> list1 = cr1.list();
		for (Pancard pancard : list1) {
			System.out.println(pancard);
			System.out.println("\t" + pancard.getPerson());
		}

	}

}

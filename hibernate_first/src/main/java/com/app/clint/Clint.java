package com.app.clint;
import java.util.List;

import javax.persistence.Tuple;

import java.util.Arrays;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CountProjection;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import com.app.model.Vendor;
import com.app.utlis.HibernateUtils;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;
import ch.qos.logback.core.net.SyslogOutputStream;

public class Clint {

	public static void save() {
		// Transient state
		Vendor vendor = new Vendor("Vinay", "pune", "12345678", "male");
		// persist state
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		session.save(vendor);
		tx.commit();
		if (tx.wasCommitted())
			System.out.println("success");
		else {
			tx.rollback();
			System.out.println("failure");
		}
		// detached state
		session.close();
	}

	public static void getById() {
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		Vendor vendor = (Vendor) session.get(Vendor.class, 1);
		System.out.println(vendor);
		tx.commit();
		if (tx.wasCommitted())
			System.out.println("success");
		else {
			tx.rollback();
			System.out.println("failure");
			return;
		}
	}

	public static void update() {
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		Vendor vendor = (Vendor) session.get(Vendor.class, 1);

		vendor.setName("Vaibhav Battewad");
		vendor.setAddress("warje pune");
		session.update(vendor);
		tx.commit();

		if (tx.wasCommitted()) {
			Vendor vendor1 = (Vendor) session.get(Vendor.class, 1);
			System.out.println(vendor1);
			System.out.println("success");
		} else {
			tx.rollback();
			System.out.println("failure");
		}
		session.close();
		session.flush();
		session.clear();

	}

	public static void delete() {
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		Vendor vendor = (Vendor) session.get(Vendor.class, 1);
		session.delete(vendor);
		tx.commit();
		if (tx.wasCommitted()) {
			System.out.println("Success");
		} else {
			tx.rollback();
			System.out.println("failure");
		}
	}

	public static void saveOrUpdateMethod() {
		Vendor vendor = new Vendor(1, "Mahesh", "Magarpatta, pune-14", "23456", "male");
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		tx.commit();
		if (tx.wasCommitted()) {
			System.out.println("Success");
		} else {
			tx.rollback();
			System.out.println("failure");
		}
	}

	public static void persistMethod() {
		Vendor vendor = new Vendor("Mahesh", "Kharadi,pune-14", "123456789", "male");
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		session.persist(vendor);
		tx.commit();
		if (tx.wasCommitted()) {
			System.out.println("Success");
		} else {
			tx.rollback();
			System.out.println("failure");
		}
	}

	public static void mergeObject() {
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		Vendor vendor = (Vendor) session.get(Vendor.class, 1);
		tx.commit();
		session.close();
		vendor.setName("Vishal Boinol");
		vendor.setAddress("Karve-nagar, pune17");
		// session.update(vendor);
		//// Exception in thread "main"
		// org.hibernate.SessionException: Session is closed!

		Session session1 = HibernateUtils.getConfiguration();
		Transaction tx1 = session1.beginTransaction();
		Vendor vendor1 = (Vendor) session1.get(Vendor.class, 1);
		session1.merge(vendor);
		tx1.commit();
		session1.close();
	}

	public static void restrications() {
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		// Persistence state
		Criteria cr = session.createCriteria(Vendor.class);
		// Select * from vendor;
		// List<Vendor> list = cr.list();
		// list.forEach(System.out::println);
		//Criterion crit = Restrictions.eq("name", "Sachin");
		//cr.add(Restrictions.eq("name", "Sachin"));
		// cr.add(Restrictions.like("name", "%M%"));
		// cr.add(Restrictions.isNotEmpty("name"));

		
		 cr.add(Restrictions.ne("id", 2)); 
		 cr.list().forEach(System.out::println);
			/*
			 * Vendor vendor = (Vendor) cr.uniqueResult(); System.out.println(vendor);
			 */
		  
		 tx.commit();
		 session.close();

	}
	
	public static void orderBy() {
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		
		Criteria cr= session.createCriteria(Vendor.class);
		//cr.addOrder(Order.desc("name"));
		cr.addOrder(Order.asc("name"));
		cr.list().forEach(System.out::println);
		tx.commit();
		session.close();
	}
	
	public static void pagination() {
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		
		Criteria cr = session.createCriteria(Vendor.class);
		cr.setFirstResult(3);//left 3rd and record starts from 4 upto remainig data as we mentioned  at setMaxResults
		cr.setMaxResults(3); //fixed 3,4,10,20,50
		
		cr.list().forEach(System.out::println);
		tx.commit();
		session.close();
		
	}
	@SuppressWarnings("unchecked")
	public static void projections(){
		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Vendor.class);
		
		CountProjection cp=Projections.count("id");
				 cr.setProjection(Projections.count("id"));
				 Long i=(Long)cr.uniqueResult(); 
				  System.out.println(i);
				 
				 cr.list().forEach(System.out::println);

		
	}
	
	@SuppressWarnings("unchecked")
	public static void mixCriteria() {

		Session session = HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();

		Criteria cr = session.createCriteria(Vendor.class);
		cr.add(Restrictions.gt("id", 3));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("id"));
		projectionList.add(Projections.property("name"));
		cr.setProjection(projectionList);
		cr.addOrder(Order.asc("name"));
		//cr.list().forEach(System.out::println);
	
		
		 // Using a custom ResultTransformer
        List<Vendor> vendors = cr.setResultTransformer(new ResultTransformer() {
            @Override
            public Object transformTuple(Object[] tuple, String[] aliases) {
                Vendor vendor = new Vendor();
                vendor.setId((Integer) tuple[0]);
                vendor.setName((String) tuple[1]);
                return vendor;
            }

            @Override
            public List transformList(List collection) {
                return collection;
            }
        }).list();

        // Print the result
        vendors.forEach(System.out::println);

        tx.commit();
        session.close();
    }

	
	
	
	
	@SuppressWarnings("unchecked")
	
	public static void hql() {
		
		        Session session = HibernateUtils.getConfiguration();
		        Transaction tx = session.beginTransaction();

		        Query query = session.createQuery("select id, name from Vendor where id=1 or name='Vishal'");
		        
		        List<Vendor> vendors = query.setResultTransformer(new ResultTransformer() {
		            @Override
		            public Object transformTuple(Object[] tuple, String[] aliases) {
		                Vendor vendor = new Vendor();
		                vendor.setId((Integer) tuple[0]);
		                vendor.setName((String) tuple[1]);
		                return vendor;
		            }

		            @Override
		            public List transformList(List collection) {
		                return collection;
		            }
		        }).list();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void sql() {
		Session session =HibernateUtils.getConfiguration();
		Transaction tx = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("select vid, name from vendor where vid=1 or name='Vishal'");
        query.setResultTransformer(new ResultTransformer() {

			@Override
			public Vendor transformTuple(Object[] tuple, String[] aliases) {
				Vendor vendor = new Vendor();
				vendor.setId((Integer) tuple[0]);
				vendor.setName((String) tuple[1]);
				return vendor;
				
			}
			@Override
			public List<Vendor> transformList(List collection) {
				return collection;
			}
		}).list().forEach(System.out::println);

		tx.commit();
		session.close();
	}





			

	public static void main(String[] args) {
		// save();
		// getById();
		// update();
		// delete();
		// saveOrUpdateMethod();
		// persistMethod();
		// mergeObject();
		//restrications();
		//orderBy();
		//pagination();
		//projections();
		//mixCriteria();
		//hql();
		//sql();
		
	}
}

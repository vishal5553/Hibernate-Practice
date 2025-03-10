package com.app.clint;

import org.hibernate.Session;

import com.app.model.Vendor;
import com.app.utlis.HibernateUtils;

public class GetAndLoad {
	
	public static void getMethod() {
		Session session = HibernateUtils.getConfiguration();

		System.out.println("load the vendor object");
		Vendor vendor = (Vendor) session.get(Vendor.class, 1);
		System.out.println(vendor.getId());
		System.out.println(vendor.getName());
		System.out.println(vendor.getMobile());
		System.out.println(vendor.getGender());

		session.flush();
		/*
		 * load the vendor object Hibernate: select vendor0_.vid as vid0_0_,
		 * vendor0_.name as name0_0_, vendor0_.address as address0_0_, vendor0_.mobile
		 * as mobile0_0_, vendor0_.gender as gender0_0_ from hibernate.vendor vendor0_
		 * where vendor0_.vid=? 1 Vishal Boinol 12345678 male
		 */
	}

	
	public static void loadMethod() {
		Session session = HibernateUtils.getConfiguration();

		System.out.println("load the vendor object");
		Vendor vendor = (Vendor) session.load(Vendor.class, 1);
		System.out.println(vendor.getId());
		System.out.println(vendor.getName());
		System.out.println(vendor.getMobile());
		System.out.println(vendor.getGender());

		session.flush();
		//it will return proxy first
		/*
		 * load the vendor object 1 Hibernate: select vendor0_.vid as vid0_0_,
		 * vendor0_.name as name0_0_, vendor0_.address as address0_0_, vendor0_.mobile
		 * as mobile0_0_, vendor0_.gender as gender0_0_ from hibernate.vendor vendor0_
		 * where vendor0_.vid=? Vishal Boinol 12345678 male
		 */
	}
	
	
	public static void firstLevelCache() {
		Session session = HibernateUtils.getConfiguration();

		System.out.println("first time get from db");
		Vendor vendor = (Vendor) session.load(Vendor.class, 1);
		System.out.println(vendor);
		System.out.println("second time get from cache");
		Vendor vendor1 = (Vendor) session.load(Vendor.class, 1);
		System.out.println(vendor1);

		session.flush();
		/*
		 * first time get from db Hibernate:
		 *  select vendor0_.vid as vid0_0_,
		 * vendor0_.name as name0_0_, vendor0_.address as address0_0_, vendor0_.mobile
		 * as mobile0_0_, vendor0_.gender as gender0_0_ from hibernate.vendor vendor0_
		 * where vendor0_.vid=? vendor [id=1, name=Vishal Boinol, address=Karve-nagar,
		 * pune17, mobile=12345678, gender=male] 
		 * second time get from cache vendor
		 * [id=1, name=Vishal Boinol, address=Karve-nagar, pune17, mobile=12345678,
		 * gender=male]
		 */
	}

	

	public static void main(String[] args) {
		
		//getMethod();
        //loadMethod();
		//firstLevelCache();
	}

}

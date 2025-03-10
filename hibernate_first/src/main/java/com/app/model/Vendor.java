package com.app.model;

import java.io.Serializable;

public class Vendor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5469337390135469295L;
	private int id;
	private String name;
	private String address;
	private String mobile;
	private String gender;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "vendor [id=" + id + ", name=" + name + ", address=" + address + ", mobile=" + mobile + ", gender="
				+ gender + "]";
	}
	public Vendor(int id, String name, String address, String mobile, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.gender = gender;
	}
	public Vendor(String name, String address, String mobile, String gender) {
		super();
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.gender = gender;
	}
	public Vendor() {
		super();
	}
	
	
	

}
